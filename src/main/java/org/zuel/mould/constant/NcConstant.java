package org.zuel.mould.constant;

public final class NcConstant {

    public static String SINGLE_PROB_FILE = "SingleProbFile";

    public static String MULTI_PROB_FILES = "MultiProbFiles";

    // 探针文件生成结果
    public static String PROB_HANDLE_RESULT = "O8800";

    // 加工文件处理结果前缀
    public static String PROCESS_HANDLE_PREFIX = "O";

    // 探针文件名前缀
    public static String PROB_FILE_NAME_PREFIX = "TANZHEN";

    // 探针文件开始指令
    public static String PROB_FILE_BEGIN_INS = "M18";

    // 探针文件结束指令
    public static String PROB_FILE_END_INS = "M30";

    // 探针起始标志
    public static String PROB_FILE_RESULT_TAG = "N";

    // 探针标志前缀
    public static String PROB_FILE_RESULT_PREFIX = "M198P";

    // 探针标志后缀
    public static String PROB_FILE_RESULT_POSTFIX = "01";

    // 文件起始符
    public static String FILE_START_TAG = "%";

    // 文件终止符
    public static String FILE_TERMINAL_TAG = "%";

    // 加工文件结束指令
    public static String PROCESS_TERMINAL_INS = "M30";

    // 探针文件尾部指令
    public static String PROCESS_FILE_END_INS = "M99";

    // 探测加工文件标识
    public static String PROCESS_DETECT_TAG = "#1";

    // 加工文件生成目录
    public static String PROCESS_HANDLE_DIR = "处理后";

    // 探测加工文件第一段处理标识
    public static String DETECT_1TH_TAG = "1th";

    // 探测加工文件第二段处理标识
    public static String DETECT_2ND_TAG = "2nd";

    // 探测加工文件第三段处理标识
    public static String DETECT_3RD_TAG = "3rd";

    // 探测加工文件第一段与第二段分隔符
    public static String DETECT_1TH_2ND_SEPARATOR = "(P";

    // 探测加工文件第二段与第三段分隔符
    public static String DETECT_2ND_3RD_SEPARATOR = "G21";

    // 探测加工文件第二段与第三段分隔符长度
    public static int DETECT_1TH_2ND_SEPARATOR_LENGTH = 6;

    // 加工文件旧固定路径
    public static String PROCESS_SOURCE_FIXED_PATH = "G00G90G54";

    // 加工文件新固定路径
    public static String PROCESS_TARGET_FIXED_PATH = "G90G54.1P";

    // 加工文件新固定路径新行头
    public static String PROCESS_FIXED_PATH_PREFIX = "G68X0Y0R#";

    // 加工文件新固定路径新行初始值
    public static int PROCESS_PATH_INIT_VALUE = 770;

    // 探测加工文件第三段头
    public static String DETECT_3RD_HEADER_END_TAG = "G05.1Q1";

    // 探测加工文件第二段if分支
    public static String DETECT_2ND_BRANCH_IF = "IF";

    // 探测加工文件第二段goto分支
    public static String DETECT_2ND_BRANCH_GOTO = "GOTO";

    // 探测加工文件第三段替换源命令
    public static String DETECT_3RD_REPLACE_SOURCE_INS = "G43";

    // 探测加工文件第三段替换目标命令
    public static String DETECT_3RD_REPLACE_TARGET_INS = "G43H00";

}
