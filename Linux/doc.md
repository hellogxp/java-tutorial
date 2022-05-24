## Doc

### Add GBK encoding for Linux (Centos 7)    
Many times in the files we uploaded to the linux, the Chinese content will appear garbled. We could solve this follow 
this guide:

1. Check if the GBK language package is installed:    
```
locale  -a
```
if there is no Chinese package available, we need to install firstly:    
```shell
yum install kde-l10n-Chinese -y
```

2. Set environment variables    
```shell
 vim  /etc/profile
 export LC_ALL="zh_CN.UTF-8"
 #  update profile to take effect immediately
 source /etc/profile
```
Currently, the OS would support Chinese, and we can check with below command:    

```shell
locale
```

3. Set `locale.conf`
```shell
vim /etc/locale.conf 
LANG="zh_CN.UTF-8"
source /etc/locale.conf 
```

4. Set system language    
```shell
localedef -c -f UTF-8 -i zh_CN zh_CN.utf8
```
At last, we maybe need to `reboot` OS, after that Chinese would be display normally.

