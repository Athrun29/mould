# mould

***************

## 背景

汽车模具制造厂各生产环节均由各自自动化程序完成，但上一个环节程序生成的结果不能直接交付下一环节程序作为输入，需要人工干预，即手动修改文件。由于人工干预效率低下且容易出错，交由本系统代替人工进行干预。

***************

## 功能设计
```
-- 功能
    |-- 钻刀前缀维护
        |-- 钻刀前缀列表
        |-- 钻刀前缀详情
        |-- 编辑钻刀前缀
        |-- 删除刀具前缀
    |-- 刀具维护
        |-- 刀具信息列表
        |-- 刀具信息详情
        |-- 编辑刀具信息
        |-- 删除刀具
    |-- 文件处理
```
***************

## 数据库设计

### 字典表 base_dic

| column | type | comments |
| :--- | :------: | :------: | 
| id | bigint(20)| 主键 | 
| code | varchar(255) | 编号 | 
| name | varchar(255) | 名称 |
| parent | bigint(20) | 父主键 |
| remark | varchar(255) | 备注 |

### 刀具表 knife_general

| column | type | comments |
| :--- | :------: | :------: | 
| id | bigint(20)| 主键 | 
| code | varchar(255) | 编号 | 
| name | varchar(255) | 名称 |
| dia | double(20, 4) | 直径 |
| rad | double(20, 4) | 半径 |
| len | double(20, 4) | 长度 |
| is_del | int(1) | 删除标记 |
| remark | varchar(255) |备注 |
| timestamp | datetime | 时间戳 |

### 替换记录表 replace_record

| column | type | comments |
| :--- | :------: | :------: | 
| id | bigint(20)| 主键 | 
| src_name | varchar(255) | 原名称 |
| src_dia | double(20, 4) | 原直径 |
| src_rad | double(20, 4) | 原半径 |
| src_len | double(20, 4) | 原长度 |
| tar_name | varchar(255) | 新名称 |
| tar_dia | double(20, 4) | 新直径 |
| tar_rad | double(20, 4) | 新半径 |
| tar_len | double(20, 4) | 新长度 |
| remark | varchar(255) |备注 |
| create_time | datetime | 替换时间 |

***************

## 接口设计

### 1.钻刀前缀维护

#### 1.1 钻刀前缀列表  

```
url: /app/dic/glassCutter/list
method: POST
param: json
{
    pager: {
        pageNum: 0, 
        pageSize: 0, 
        totalNum: 0
    }, 
    queryModel: {
        code: '编号', 
        name: '名称'
    }
}
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: {
        pager: {
           pageNum: 0, 
           pageSize: 0, 
           totalNum: 0
        }, 
        datas: [
            {
                id: 0, 
                code: '编号', 
                name: '名称', 
                remark: '备注'
            }
        ]
    }
}           
```

#### 1.2 钻刀前缀详情

```
url: /app/dic/glassCutter/get/{id}
method: GET
param: 刀具前缀信息id
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: {
        id: 0, 
        code: '编号', 
        name: '名称', 
        remark: '备注'
    }
} 
```

#### 1.3 编辑钻刀前缀

```
url: /app/dic/glassCutter/save
method: POST
param: json
{
    id: null, 
    code: '', 
    name: '', 
    remark: ''
}
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: {
        id: 0, 
        code: '编号', 
        name: '名称', 
        remark: '备注'
    }
} 
```

#### 1.4 删除钻刀前缀

```
url: /app/dic/glassCutter/del
method: POST
param: json
[
    {
        id: 0
    }
]
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: null
} 
```

### 2. 刀具信息维护

#### 2.1 刀具信息列表

```
url: /app/cutter/list
method: POST
param: json
{
    pager: {
        pageNum: 0, 
        pageSize: 0, 
        totalNum: 0
    }, 
    queryModel: {
        code: '编号', 
        name: '名称'
    }
}
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: {
        pager: {
           pageNum: 0, 
           pageSize: 0, 
           totalNum: 0
        }, 
        datas: [
            {
                id: 0, 
                code: '编号', 
                name: '名称', 
                dia: 0.0,  // 直径
                rad: 0.0,  // 半径
                len: 0.0,  // 长度
                remark: '备注'
            }
        ]
    }
}           
```
#### 2.2 刀具信息详情

```
url: /app/cutter/get/{id}
method: GET
param: 刀具信息id
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: {
        id: 0, 
        code: '编号', 
        name: '名称', 
        dia: 0.0,  // 直径
        rad: 0.0,  // 半径
        len: 0.0,  // 长度
        remark: '备注'
    }
} 
```
#### 2.3 编辑刀具信息

```
url: /app/cutter/save
method: POST
param: json
{
    id: 0, 
    code: '', 
    name: '', 
    dia: 0.0,  // 直径
    rad: 0.0,  // 半径
    len: 0.0,  // 长度
    remark: ''
}
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: {
        id: 0, 
        code: '编号', 
        name: '名称', 
        dia: 0.0,  // 直径
        rad: 0.0,  // 半径
        len: 0.0,  // 长度
        remark: '备注'
    }
} 
```
#### 2.4 删除刀具信息

```
url: /app/cutter/del
method: POST
param: json
[
    {
        id: 0
    }
]
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: null
} 
```

### 3. 替换记录

#### 3.1 替换记录列表

```
url: /app/cutter/list
method: POST
param: json
{
    pager: {
        pageNum: 0, 
        pageSize: 0, 
        totalNum: 0
    }, 
    queryModel: {
        srcName: '原名称', 
        tarName: '新名称'
    }
}
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: {
        pager: {
           pageNum: 0, 
           pageSize: 0, 
           totalNum: 0
        }, 
        datas: [
            {
                id: 0, 
                srcName: '原名称',
                srcDia: 0.0,  // 原直径
                srcRad: 0.0,  // 原半径
                srcLen: 0.0,  // 原长度
                tarName: '新名称', 
                tarDia: 0.0,  // 新直径
                tarRad: 0.0,  // 新半径
                tarLen: 0.0,  // 新长度
                remark: '备注', 
                createTime: '创建时间' 
            }
        ]
    }
}           
```
### 4. 文件处理

#### 1.1 nc文件处理

```
url: /app/job/ncJob
method: POST
param: json
{
    input: '输入路径', 
    output: '输出路径'
}
return: json
{
    code: 200, 
    success: 1, 
    msg: '结果信息', 
    data: null
} 
```
