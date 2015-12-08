// default package (CtPackage.TOP_LEVEL_PACKAGE_NAME in Spoon= unnamed package)



public class Foo {
    public boolean op(java.lang.Boolean a, java.lang.Boolean b) {
        return ((_bo1.is("OR") && (a || b)) || (_bo1.is("AND") && (a && b)));
    }

    public boolean op2(java.lang.Integer a, java.lang.Integer b) {
        return ((_bo2.is("EQ") && (a == b)) || (_bo2.is("NE") && (a != b)) || (_bo2.is("LT") && (a < b)) || (_bo2.is("GT") && (a > b)) || (_bo2.is("LE") && (a <= b)) || (_bo2.is("GE") && (a >= b)));
    }

    public boolean op3(java.lang.Class c) {
        return ((_bo3.is("EQ") && ((Foo.class) == c)) || (_bo3.is("NE") && ((Foo.class) != c)));
    }

    private static final metamutator.Selector _bo1 = metamutator.Selector.of(11,new String[]{"OR","AND"}).in(Foo.class).id("_bo1");

    private static final metamutator.Selector _bo2 = metamutator.Selector.of(12,new String[]{"GT","EQ","NE","LT","LE","GE"}).in(Foo.class).id("_bo2");

    private static final metamutator.Selector _bo3 = metamutator.Selector.of(13,new String[]{"EQ","NE"}).in(Foo.class).id("_bo3");
}

