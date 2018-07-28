package org.zuel.mould.handler;

import java.io.IOException;
import java.util.List;

public interface IProbFileHandler {

    void handleProbFile(String basePath, List<String> probFilePath) throws IOException;
}
