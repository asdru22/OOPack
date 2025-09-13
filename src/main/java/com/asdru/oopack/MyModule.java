package com.asdru.oopack;

import com.asdru.oopack.objects.data.Function;
import com.asdru.oopack.util.Util;

public class MyModule extends Module {

    @Override
    void content() {
        Function.f.of("test", "function %s", Function.f.of("say hji"));
        Util.addTranslation("a", "b");

    }
}
