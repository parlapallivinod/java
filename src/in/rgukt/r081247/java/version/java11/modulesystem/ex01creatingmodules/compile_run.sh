rm -rf ./out
javac --module-source-path src -d out $(find . -name '*.java')
java --module-path out -m in.rgukt.r081247r.java.samplemodule/in.rgukt.r081247.java.samplepackage.Main
