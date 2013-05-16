(ns ^{:doc "Tests for Pandect"
      :author "Yannick Scherer"}
  pandect.core-test
  (:use midje.sweet
        pandect.core))

(tabular
  (fact "about hashing 'Hello World!'"
    (?digest "Hello World!") => ?result)
  ?digest       ?result
  md2           "315f7c67223f01fb7cab4b95100e872e"
  md5           "ed076287532e86365e841e92bfc50d8c"
  sha1          "2ef7bde608ce5404e97d5f042f95f89f1c232871"
  sha256        "7f83b1657ff1fc53b92dc18148a1d65dfc2d4b1fa3d677284addd200126d9069" 
  sha384        "bfd76c0ebbd006fee583410547c1887b0292be76d582d96c242d2a792723e3fd6fd061f9d5cfd13b8f961358e6adba4a"
  sha512        "861844d6704e8573fec34d967e20bcfef3d424cf48be04e6dc08f2bd58c729743371015ead891cc3cf1c9d34b49264b510751b1ff9e537937bc46b5d6ff4ecc8")
