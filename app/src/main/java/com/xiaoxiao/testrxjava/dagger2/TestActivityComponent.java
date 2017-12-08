package com.xiaoxiao.testrxjava.dagger2;

import com.xiaoxiao.testrxjava.TestActivity;
import dagger.Subcomponent;

/**
 * Created by caixiaoxiao on 1/3/17.
 */
@Subcomponent
public interface TestActivityComponent {
    void inject(TestActivity testActivity);
}
