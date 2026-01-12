package com.wdiscute.starchaeology.datagen.Antiqua;


import com.wdiscute.starchaeology.U;
import com.wdiscute.starchaeology.io.AntiquaProperties;

import static com.wdiscute.starchaeology.datagen.Antiqua.AntiquaPropertiesRegistry.*;

public class DGMinecraftAntiqua
{
    public static void bootstrap() {

        //ocean
        register(overworldAntiqua(U.holderItem("minecraft", "diamond"))
                .withDifficulty(AntiquaProperties.Difficulty.DEFAULT)
        );
    }
}
