package configuration;


public class ClasseB {
    public ClasseB() {
    }

    public java.lang.String s() {
        java.lang.String toto = ((_nv1.is("NOTNULL"))?"toto":null);
        return toto;
    }

    public boolean op(java.lang.Boolean a, java.lang.Boolean b) {
        return ((_bo1.is("OR") && (a || b)) || (_bo1.is("AND") && (a && b)));
    }

    public boolean op2(java.lang.Integer a, java.lang.Integer b) {
        return ((_bo2.is("EQ") && (a == b)) || (_bo2.is("NE") && (a != b)) || (_bo2.is("LT") && (a < b)) || (_bo2.is("GT") && (a > b)) || (_bo2.is("LE") && (a <= b)) || (_bo2.is("GE") && (a >= b)));
    }

    public boolean op3(java.lang.Class c) {
        return ((_bo3.is("EQ") && ((getClass()) == c)) || (_bo3.is("NE") && ((getClass()) != c)));
    }

    private static final metamutator.Selector _bo1 = metamutator.Selector.of(1,new String[]{"OR","AND"}).in(ClasseB.class).id("_bo1");

    private static final metamutator.Selector _bo2 = metamutator.Selector.of(2,new String[]{"GT","EQ","NE","LT","LE","GE"}).in(ClasseB.class).id("_bo2");

    private static final metamutator.Selector _bo3 = metamutator.Selector.of(3,new String[]{"EQ","NE"}).in(ClasseB.class).id("_bo3");

    private static final metamutator.Selector _nv1 = metamutator.Selector.of(1,new String[]{"NOTNULL","NULL"}).in(ClasseB.class).id("_nv1");
}

