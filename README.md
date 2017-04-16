# mosquito

## 链接数
watch -n 1 'netstat -nat | grep "8081" | wc -l | xargs ./curl_info.sh'
``` curl_info.sh
#!/bin/bash
if [ $1 -gt 1 ];then
    curl http://报警地址
fi
```

<img src="./src/main/resources/public/images/role_add.jpeg" width="850px" />
<img src="./src/main/resources/public/images/role_stat.jpeg" width="850px" />