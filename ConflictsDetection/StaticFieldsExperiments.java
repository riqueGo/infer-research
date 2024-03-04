package ConflictsDetection;

public class StaticFieldsExperiments {
    private static int staticField = 0;

    //############################### there should be no conflict ###############################

    //When a field in config as source is used by sink method, it gets error 
    public void leftDefFieldAndRightUseItInferNotConflict() {
        System.out.println(Infer.useRight(staticField)); //right
    }

    //############################### Conflict ###############################

    //Conflict using fields config
    public void leftDefFieldAndRightUseItInferConflict() {
        staticField = 1; //left
        ExperimentsHelper.base();
        System.out.println(Infer.useRight(staticField)); //right
    }

}
