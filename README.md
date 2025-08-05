# OOPACK
Java Library to increase productivity in minecraft pack development

## Problems encountered
- How to handle context: where objects are added in the project, who is the parent, ecc
- how to get namespace of children when created to be immediately used in the toString method: had to pass a reference to namespace at the parent object,  so that when the child is added, the namespace is automatically assigned. 

## TODO
- Build project as zip
- try to remove unused interface methods from namespace
- load mcmeta versions from web
- improve constructors in pack objects