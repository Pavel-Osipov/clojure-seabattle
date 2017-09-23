(defproject seabattle "0.1.0-SNAPSHOT"
  :description "Clojure learning seabattle project"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.8.0"]]

  :main ^:skip-aot seabattle.core

  :target-path "target/%s"
  :test-paths ["test"]

  :profiles
    {:uberjar {:aot :all}
     :dev
      [{:plugins [[lein-kibit "0.1.3"]
                  [jonase/eastwood "0.2.3"]
                  [lein-cljfmt "0.5.7"]]}]})
