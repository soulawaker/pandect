(ns ^{:doc "Digest Creation for Pandect"
      :author "Yannick Scherer"}
  pandect.core
  (:require [pandect.gen
             [core :refer [generate code-generator]]
             [hash-generator :refer [hash-generator]]
             [hmac-generator :refer [hmac-generator]]
             message-digest
             checksum
             bouncy-castle]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

;; ## Available Algorithms

(def ^:private algorithms
  '{md5       "MD5"           md2       "MD2"
    md4       "MD4"           gost      "GOST 34.11-94"
    sha1      "SHA-1"         sha224    "SHA-224"
    sha256    "SHA-256"       sha384    "SHA-384"
    sha512    "SHA-512"       sha3-256  "SHA-3 (256)"
    sha3-224  "SHA-3 (224)"   sha3-384  "SHA-3 (384)"
    sha3-512  "SHA-3 (512)"   whirlpool "Whirlpool"
    adler32   "ADLER-32"      crc32     "CRC-32"
    ripemd128 "RIPEMD-128"    ripemd160 "RIPEMD-160"
    ripemd256 "RIPEMD-256"    ripemd320 "RIPEMD-320"
    tiger     "Tiger (192,3)" sip-hash  "SipHash-2-4"})

(def ^:private generators
  [hash-generator
   hmac-generator])

(defmacro ^:private generate-hash-functions!
  []
  `(do
     ~@(for [[sym algorithm] algorithms
             generator generators
             :let [code-gen (code-generator algorithm)]
             :when code-gen]
         (generate generator code-gen sym))
     nil))

(generate-hash-functions!)

;; ## Buffer Size

(defmacro with-buffer-size
  "Set buffer size for stream processing."
  [n & body]
  `(binding [*buffer-size* ~n]
     ~@body))
