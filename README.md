# mosquito

## 链接数
watch -n 1 'netstat -nat | grep "8081" | wc -l | xargs ./curl_info.sh'
``` curl_info.sh
#!/bin/bash
if [ $1 -gt 1 ];then
    curl http://报警地址
fi
```
