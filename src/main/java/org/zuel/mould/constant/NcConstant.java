package org.zuel.mould.constant;

public final class NcConstant {

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
    public static String PROCESS_HANDLE_DIR = "result";

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

    // 刀具头
    public static String KNIFE_TOOL_INFO_HEAD = "T";

    // 刀具直径
    public static String KNIFE_TOOL_INFO_DIA = "D.";

    // 刀具半径
    public static String KNIFE_TOOL_INFO_RAD = "R.";

    // 刀具长度
    public static String KNIFE_TOOL_INFO_LEN = "MINLEN";

    // 刀具信息起始字符
    public static String KNIFE_TOOL_START_CHAR = "(";

    // 刀具信息开始标志
    public static String KNIFE_TOOL_START_TAG = ":";

    // 刀具信息结束字符
    public static String KNIFE_TOOL_END_CHAR = ")";

    // 刀具信息与编号间隔行数
    public static int KNIFE_TOOL_HEAD_INTERVAL = 5;

    // 刀具编号与尾部间隔行数
    public static int KNIFE_TOOL_TAIL_INTERVAL = 8;

    // 刀具尾部标志
    public static String KNIFE_TOOL_TAIL_TAG = "G43H00";

    // 刀具尾部内容
    public static String KNIFE_TOOL_TAIL_STR = "G41D";

    // 刀具信息占位编号
    public static String KNIFE_TOOL_OCCUPY_CODE = "00";

    // 刀具信息修改字符 H
    public static String KNIFE_TOOL_OCCPY_CHAR_H = "H";

    // 刀具信息修改字符 D
    public static String KNIFE_TOOL_OCCPY_CHAR_D = "D";

    // 探针刀具名称
    public static String KNIFE_TOOL_PROB_NAME = "TANZHEN";

    // 刀具信息最大数量
    public static int KNIFE_TOOL_MAX_NUM = 24;

    // 刀具处理日志前缀
    public static String NC_ERROR_LOG_PREFIX = "err_log_";

    // 刀具处理日志前缀
    public static String NC_ERROR_LOG_POSTFIX = ".txt";

    // 刀具处理日志分割线
    public static String NC_ERROR_LOG_LINE_SEPARATOR = "------------------------";

    // 时间格式化紧凑格式
    public static String DATE_FORMAT_MINI = "yyyyMMddHHmmss";

    // 钻刀父id
    public static long GLASS_CUTTER_ROOT_ID = 1;

    // 刀具特殊名称标志
    public static String KNIFE_TOOL_SPCL_NAME_TAG = "_";

    // 刀具名称长度标志
    public static String KNIFE_TOOL_NAME_LENGTH_TAG = "L";

}
