package temporaire;


public class ClasseA {
    public ClasseA() {
    }

    public java.lang.String s() {
        java.lang.String toto = ((_variableNullHotSpot1.is(metamutator.Null.NO))?"toto":null);
        return toto;
    }

    private static final metamutator.Selector _variableNullHotSpot1 = metamutator.Selector.of(21,new metamutator.Null[]{metamutator.Null.NO,metamutator.Null.YES}).in(temporaire.ClasseA.class).id("_variableNullHotSpot1");
}

