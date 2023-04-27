package nure.pancake.game.shop.gameproductservice.mappers;

public class ServiceUrlFactoryImpl implements ServiceUrlFactory{
    private final String url;


    public ServiceUrlFactoryImpl(String host, String port, String contextPath) {
        this.url = "http://" + host + ":" + port + contextPath;
    }

    @Override
    public String makeUrl(String path) {
        return url +  path + "/";
    }
}
