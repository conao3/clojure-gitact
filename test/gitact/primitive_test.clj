(ns gitact.primitive-test
  (:require
   [clojure.test :as t]
   [gitact.primitive :as sut]))

(t/deftest glob-match?-test
  (t/is (= false (sut/glob-match? "*.js" "abcd")))
  (t/is (= true (sut/glob-match? "*.js" "a.js")))
  (t/is (= false (sut/glob-match? "*.js" "a.md")))
  (t/is (= false (sut/glob-match? "*.js" "a/b.js")))

  (t/is (= true (sut/glob-match? "*.md" ".c.md"))) ; false in picomatch
  (t/is (= true (sut/glob-match? "*.md" ".md"))) ; false in picomatch

  (t/is (= false (sut/glob-match? "*.md" "a/b/c.md")))
  (t/is (= false (sut/glob-match? ".md" "a/b/c.md")))
  (t/is (= false (sut/glob-match? "a/*.md" "a/b/c.md")))

  (t/is (= true (sut/glob-match? ".*.md" ".c.md")))
  (t/is (= true (sut/glob-match? ".md" ".md")))
  (t/is (= true (sut/glob-match? "a/**/*.*" "a/b/c.js")))
  (t/is (= true (sut/glob-match? "**/*.md" "a/b/c.md")))
  (t/is (= true (sut/glob-match? "a/**/*.md" "a/b/c.md"))))
