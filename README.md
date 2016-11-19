# android-plugin-loopeermodel
an Android Studio plugin for generating model using loopeer data dictionary.

###loopeer data dictionary:
####define a data struct as below:
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
####Usage
1.control/comand + n call generate , and choose Loopeer Model (hot key: alt + L).  
![](/screenshot/screen-shot-1.png)  
2.paste data dictionary text into the edit box.  
![](/screenshot/screen-shot-2.png)  
![](/screenshot/screen-shot-3.png) 
3.press confirm.  

####Todo-List
- **features**
- [ ] loopeer图标
- [ ] 自动导入annotations包
- [ ] 如果类名存在，则自动生成类
- [ ] 可编辑单独每个变量的各个属性
- [ ] 自定义变量名规则
- **optimize**
- [ ] 用正则表达式优化数据合法性检验
