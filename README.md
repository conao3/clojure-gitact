# gitact

A Clojure library providing Git utility functions for detecting changed files and matching file paths with glob patterns.

## Installation

Add the following dependency to your `deps.edn`:

```clojure
com.github.conao3/gitact {:mvn/version "0.1.XXX"}
```

Or with a Git dependency:

```clojure
com.github.conao3/gitact {:git/url "https://github.com/conao3/clojure-gitact"
                          :git/sha "XXXXXXX"}
```

## Usage

### Getting Changed Files

Retrieve a list of files that have changed compared to a specified branch:

```clojure
(require '[gitact.primitive :as gitact])

;; Get all changed files compared to main branch
(gitact/get-changed-files "main")
;; => ["src/core.clj" "test/core_test.clj"]
```

This function combines the output of `git diff --name-only` and `git ls-files --others --exclude-standard` to include both modified tracked files and new untracked files.

### Glob Pattern Matching

Match file paths against glob patterns:

```clojure
(require '[gitact.primitive :as gitact])

;; Simple extension matching
(gitact/glob-match? "*.js" "app.js")      ;; => true
(gitact/glob-match? "*.js" "style.css")   ;; => false

;; Recursive matching with **
(gitact/glob-match? "**/*.md" "docs/guide/intro.md")  ;; => true
(gitact/glob-match? "src/**/*.clj" "src/app/core.clj") ;; => true
```

## Requirements

- Clojure 1.11.3 or later
- Git (available in PATH)

## License

Copyright 2024 conao3

Licensed under the Apache License, Version 2.0. See [LICENSE](LICENSE) for details.
