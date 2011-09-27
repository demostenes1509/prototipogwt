find . -name ".project" -print -exec rm -fR {} \;			2>/dev/null
find . -name ".classpath" -print -exec rm -fR {} \;			2>/dev/null
find . -name ".settings" -print -exec rm -fR {} \;			2>/dev/null
find . -name "target" -print -exec rm -fR {} \;				2>/dev/null
find . -name "test-output" -print -exec rm -fR {} \;		2>/dev/null
find . -name ".gwt" -print -exec rm -fR {} \;				2>/dev/null
