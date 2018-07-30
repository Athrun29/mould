package org.zuel.mould.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.zuel.mould.bean.KnifeGeneral;
import org.zuel.mould.constant.NcConstant;
import org.zuel.mould.service.IKnifeToolService;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ToolReplaceHandler {

    @Autowired
    private IKnifeToolService knifeToolService;

    /**
     * 处理刀具信息
     * @param resultPath
     * @throws IOException
     */
    public void handleToolInfo(String resultPath) throws IOException {
        List<String>  resultLines = Files.readAllLines(Paths.get(resultPath));
        FileWriter writer = new FileWriter(resultPath, false);
        for(int i = 0; i < resultLines.size(); ++i) {
            if(resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_HEAD) && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_DIA)
                    && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_RAD) && resultLines.get(i).contains(NcConstant.KNIFE_TOOL_INFO_LEN)) {
                String[] arrVal = resultLines.get(i).split("\\ ");
                BigDecimal dia = BigDecimal.valueOf(Double.valueOf(arrVal[1].split("\\=")[1]));
                BigDecimal rad = BigDecimal.valueOf(Double.valueOf(arrVal[2].split("\\=")[1]));
                BigDecimal len = BigDecimal.valueOf(Double.valueOf(arrVal[5].split("\\=")[1].replace(NcConstant.KNIFE_TOOL_END_CHAR, "")));
                if(dia.doubleValue() == 0 && rad.doubleValue() == 0) {
                    writer.write(resultLines.get(i));
                } else {
                    KnifeGeneral knifeGeneral = knifeToolService.getKnifeGeneralByDiaAndRad(dia, rad);
                    if (knifeGeneral == null) {
                        writer.write(resultLines.get(i));
                    } else {
                        String replaceLine = resultLines.get(i).replace(
                                resultLines.get(i).substring(resultLines.get(i).indexOf(NcConstant.KNIFE_TOOL_START_CHAR,
                                        resultLines.get(i).indexOf(NcConstant.KNIFE_TOOL_START_TAG))),
                                NcConstant.KNIFE_TOOL_INFO_HEAD + knifeGeneral.getCode());
                        writer.write(replaceLine);
                    }
                }
            } else {
                writer.write(resultLines.get(i));
            }
        }
    }

}
