JAVA_SRCS=$(find app/src/main -name '*.java')
JAR='app.jar'
MANIFEST='MANIFEST.mf'

BUILD_DIR='builddir'
DOCS_DIR='build/docs'

javac -d $BUILD_DIR $JAVA_SRCS

javadoc -d $DOCS_DIR $JAVA_SRCS

jar -cfm $JAR $MANIFEST -C $BUILD_DIR .
java -jar $JAR

rm -r $BUILD_DIR
rm $JAR
