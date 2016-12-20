# android-plugin-loopeermodel
an Android Studio plugin for generating model using loopeer data dictionary.

###loopeer data dictionary:
####define a data struct as below:

```
 ClassName [note] // optional
 param1：note1
 param2：note2
 ...
 ...
```
####notice:
1.**for head line** (if exist) should pass the regular expression:  
```
(###)\s\w+\s\[.+\]|\w+\s\[.+\]
```
2.**for param line** should pass the regular expression:  
```
\*\s\w+[：:].*|\w+[：:].*
``` 

eg.

```
 IdentityAuth [身份认证]
 account_id：用户id
 status：状态(0-审核中 1-已通过 2-未通过)
 front_image：正面照片
 inhand_image：手持照片
```
####Usage
#####1.control/comand + n call Generate , and choose Loopeer Model (hot key: alt + L).  
![](/screenshot/screen-shot-1.png)  
#####2.paste data dictionary text into the edit box.  
![](/screenshot/screen-shot-2.png)  
#####3.press Confirm.  
![](/screenshot/screen-shot-3.png)  

####Todo-List
- **features**
- [ ] loopeer icon
- [ ] 自动导入annotations包
- [ ] 如果类名存在，则自动生成类
- [ ] 可编辑单独每个变量的各个属性
- [ ] 自定义变量名规则
- **optimize**
- [x] 用正则表达式优化数据合法性检验
