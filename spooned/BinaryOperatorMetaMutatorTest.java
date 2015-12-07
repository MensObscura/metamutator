// default package (CtPackage.TOP_LEVEL_PACKAGE_NAME in Spoon= unnamed package)



public class BinaryOperatorMetaMutatorTest {
    @org.junit.Test
    public void testBinaryOperatorMetaMutator() throws java.lang.Exception {
        spoon.Launcher l = new spoon.Launcher();
        l.addInputResource("src/test/java");
        l.addProcessor(new metamutator.BinaryOperatorMetaMutator());
        l.run();
        spoon.reflect.declaration.CtClass c = ((spoon.reflect.declaration.CtClass)(l.getFactory().Package().getRootPackage().getElements(new spoon.reflect.visitor.filter.NameFilter("Foo")).get(0)));
        java.lang.System.out.println("// Metaprogram: ");
        java.lang.System.out.println(c.toString());
        bsh.Interpreter bsh = new bsh.Interpreter();
        org.junit.Assert.assertEquals(0, metamutator.Selector.getAllSelectors().size());
        java.lang.Object o = ((java.lang.Class)(bsh.eval(c.toString()))).newInstance();
        org.junit.Assert.assertEquals(3, metamutator.Selector.getAllSelectors().size());
        metamutator.Selector sel = metamutator.Selector.getSelectorByName("_bo1");
        org.junit.Assert.assertEquals(true, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op", new java.lang.Object[]{ java.lang.Boolean.TRUE , java.lang.Boolean.FALSE }));
        sel.choose(0);
        org.junit.Assert.assertEquals(true, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op", new java.lang.Object[]{ java.lang.Boolean.TRUE , java.lang.Boolean.FALSE }));
        sel.choose(1);
        org.junit.Assert.assertEquals(false, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op", new java.lang.Object[]{ java.lang.Boolean.TRUE , java.lang.Boolean.FALSE }));
        try {
            sel.choose(2);
            org.junit.Assert.fail();
        } catch (java.lang.IllegalArgumentException expected) {
        }
        metamutator.Selector sel1 = metamutator.Selector.getSelectorByName("_bo2");
        sel1.choose(0);
        org.junit.Assert.assertEquals(false, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op2", new java.lang.Object[]{ 3 , 3 }));
        org.junit.Assert.assertEquals(true, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op2", new java.lang.Object[]{ 5 , 4 }));
        sel1.choose(1);
        org.junit.Assert.assertEquals(true, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op2", new java.lang.Object[]{ 3 , 3 }));
        org.junit.Assert.assertEquals(false, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op2", new java.lang.Object[]{ 4 , 3 }));
        sel1.choose(2);
        org.junit.Assert.assertEquals(false, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op2", new java.lang.Object[]{ 3 , 3 }));
        org.junit.Assert.assertEquals(true, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op2", new java.lang.Object[]{ 4 , 3 }));
        sel1.choose(3);
        org.junit.Assert.assertEquals(false, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op2", new java.lang.Object[]{ 3 , 3 }));
        org.junit.Assert.assertEquals(true, org.apache.commons.lang.reflect.MethodUtils.invokeExactMethod(o, "op2", new java.lang.Object[]{ 3 , 4 }));
    }
}

