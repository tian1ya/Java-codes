package designMode.sixPrinciples.DanYiZhiZeP;


interface IPrettyGirl {
    void goodLooking();
    void niceFigure();
    void greatTemperament();
}

interface IGoodBodyGirls {
    void goodLooking();
    void niceFigure();
}

interface IGreatTemperament {
    void greatTemperament();
}

class PrettyGitl implements IGoodBodyGirls, IGreatTemperament{


    private String name;

    PrettyGitl(String name) {
        this.name = name;
    }

    @Override
    public void goodLooking() {
        System.out.println(this.name + "-----漂亮脸蛋");
    }

    @Override
    public void niceFigure() {
        System.out.println(this.name + "-----完美气质");
    }

    @Override
    public void greatTemperament() {
        System.out.println(this.name + "-----完美身材");
    }
}

abstract class AbstractSearcher {

    protected IPrettyGirl prettyGirl;

    public AbstractSearcher(IPrettyGirl prettyGirl) {
        this.prettyGirl = prettyGirl;
    }

    public abstract void show();
}


class Searcher extends AbstractSearcher {

    public Searcher(IPrettyGirl prettyGirl) {
        super(prettyGirl);
    }

    @Override
    public void show() {
        System.out.println("美女信息如下");

        super.prettyGirl.goodLooking();
        super.prettyGirl.niceFigure();
        super.prettyGirl.greatTemperament();
    }
}


public class SingleP {

    public static void main(String[] args) {
        PrettyGitl prettyGitl = new PrettyGitl("d");

//        Searcher searcher = new Searcher(prettyGitl);

//        searcher.show();

    }
}
