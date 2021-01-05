package proxy.static1.example;

public class Cinema implements Movie{

    private RealMovie movie;

    public Cinema(RealMovie realMovie) {
        super();
        this.movie = realMovie;
    }

    // Cinema 就是 Proxy 代理对象，它有一个 play() 方法。
    @Override
    public void play() {
        // 这里广告就是增强的功能
        guanggao(true);

        movie.play();

        guanggao(false);
    }


    public void guanggao(boolean isStart){
    	if ( isStart ) {
        System.out.println("电影马上开始了，爆米花、可乐、口香糖9.8折，快来买啊！");
    } else {
        System.out.println("电影马上结束了，爆米花、可乐、口香糖9.8折，买回家吃吧！");
    }
}
}
