package chen0040.github.com.androidmagentoclient;

import com.github.chen0040.magento.MagentoClient;
import com.github.chen0040.magento.services.HttpComponent;

/**
 * Created by chen0 on 4/7/2017.
 */

public class MagentoAndroidClient extends MagentoClient {

    public MagentoAndroidClient(String baseUri) {
        super(baseUri, new AndroidHttpComponent());
    }

    public MagentoAndroidClient(String baseUri, HttpComponent httpComponent) {
        super(baseUri, httpComponent);
    }


}
