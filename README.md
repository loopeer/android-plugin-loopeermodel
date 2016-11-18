# android-plugin-loopeermodel
an Android Studio plugin for generating model using data dictionary provided by the server developer
#### define a data struct below:
```
 ClassName [classname]
 param1：note1
 param2：note2
 ...
 ...
```
eg.

```
 IdentityAuth [身份认证]
 account_id：用户id
 status：状态(0-审核中 1-已通过 2-未通过)
 front_image：正面照片
 inhand_image：手持照片
```
#### Screenshot
![](/screenshot/generate_before.png)
![](/screenshot/generate_after.png)

#### Todo-List
- **features**
- [ ] 如果类名存在，则自动生成类
- [ ] 可编辑单独每个变量的各个属性
- [ ] 自定义变量名规则
- **optimize**
- [ ] 用正则表达式优化数据合法性检验
