package vn.tiki.test_tiki.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by nhat on 3/2/19
 */
public interface ManagerService {

    @GET("v2/keywords.json")
    Observable<List<String>> getHotKeywords();
}
