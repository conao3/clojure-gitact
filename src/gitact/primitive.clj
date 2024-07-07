(ns gitact.primitive
  (:require
   [merr.core :as merr]
   [clojure.string :as string]
   [clojure.java.shell :as shell])
  (:import
   [java.nio.file FileSystems]))

(defn merr-invoke-command [& args]
  (let [res (apply shell/sh args)]
    (if (= 0 (:exit res))
      res
      (merr/error {:data (merge args {:command args})}))))

(defn get-changed-files [branch]
  (merr/let +err+ [git1 (merr-invoke-command "git" "diff" "--name-only" branch)
                   git2 (merr-invoke-command "git" "ls-files" "--others" "--exclude-standard")]
    (or +err+
        (->> (str (:out git1) (:out git2))
             string/trim
             (#(if (= "" %) [] (string/split-lines %)))))))

(defn glob-match? [pattern string]
  (let [matcher (.getPathMatcher (FileSystems/getDefault) (str "glob:" pattern))]
    (.matches matcher (.getPath (FileSystems/getDefault) string (into-array String [])))))
