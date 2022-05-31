## Docs

### File or directory mounting

When you mount some host's directory to container's, if there is a directory with the same name in the container, the content
of this directory in container will be 'hidden' and consistent with the host.

```
version: '3'
services:
 nginx:
     image: nginx:1.8.1
     ports:              #端口映射
         - "80:80"
     volumes:
         - "${PWD}/nginx:/etc/nginx" # 
         - "${PWD}/nginx/conf.d:/etc/nginx/conf.d"   #将宿主机上nginx配置文件映射到容器中
         #- $PWD/nginx/nginx.conf:/etc/nginx/nginx.conf   #将宿主机上nginx配置文件映射到容器中
         - "${PWD}/dataweb:/var/www/html" #映射网站根目录
         - "${PWD}/nginx/log:/var/log/nginx"
     networks:
         - app_net
     container_name: "compose-nginx"  #容器名称
```

For instance, in the code snippet above, we mount the `${PWD}/nginx` (host) directory to  `/etc/nginx` (container), but there 
would be some problems, under normal circumstances, after the nginx container stared, there will be many files in this folder
`/etc/nginx` such as `mime.types`. However, we mount host's directory `${PWD}/nginx` for this container folder and there are 
no files in this folder `${PWD}/nginx`, so the container's folder need to be consistent with host's folder. Therefore, if we
start the docker, there will be some error because of file not exist. We can correct as follows:

```
version: '3'
services:
 nginx:
     image: nginx:1.8.1
     ports:              #端口映射
         - "80:80"
     depends_on:     #依赖关系，需要先运行php
         - "php"
     volumes:
         - "${PWD}/nginx/nginx.conf:/etc/nginx/nginx.conf" # 
         - "${PWD}/nginx/conf.d:/etc/nginx/conf.d"   #将宿主机上nginx配置文件映射到容器中
         #- $PWD/nginx/nginx.conf:/etc/nginx/nginx.conf   #将宿主机上nginx配置文件映射到容器中
         - "${PWD}/dataweb:/var/www/html" #映射网站根目录
         - "${PWD}/nginx/log:/var/log/nginx"
     networks:
         - app_net
     container_name: "compose-nginx"  #容器名称
```

### Access issues of directory in container

If we mount some host's directory (`./es/data`) to container's, but we do not create the directory in host, and it will be generated automatically
when the container is started, however, sometimes there would be some permission problems
```
version: '3.4'

services:
  elasticsearch:
    image: "docker.elastic.co/elasticsearch/elasticsearch:${ES_VERSION}"
    environment:
      - discovery.type=single-node
      - ELASTIC_PASSWORD=$ELASTIC_PASSWORD
      - xpack.security.enabled=$ELASTIC_SECURITY
    volumes:
      - /etc/localtime:/etc/localtime
      - ./es/data:/usr/share/elasticsearch/data
    ports:
      - "9200:9200"
      - "9300:9300"

```

For example, when the folder `./es/data` is created automatically, we also get some error response when we try to access 
es

```
"Caused by: java.nio.file.AccessDeniedException: /usr/share/elasticsearch/data/nodes",
```

1. solution one    
   Before starting the container, manually create the folder and give the correct permissions, for instance
    ```
    mkdire -p /es/data
    chomd -R a=rwx
    ```

2. solution two     
   Instead of manually creating files, you can change the permissions of the corresponding folders after the container 
starts by adding a `command` to the docker configuration file.
    ```
      command: [ 'chown' ]  
      args: [ '1000:1000', '/usr/share/elasticsearch/data' ]
    ```

### Mount file does not take effect    
Sometimes mount files do not take effect suddenly, for instance, you mount a php config file, `- "./php/php.ini:/usr/local/etc/php/php.ini"`
```shell
 php:
     build: ./php  #指定build Dockerfile生成镜像
     image: php:5.6-fpm
     ports:
         - "9000:9000"
     volumes:
         - "$PWD/dataweb:/var/www/html"
         - "./php/php.ini:/usr/local/etc/php/php.ini"
     networks:
         - app_net
     container_name: "compose-php"
```
and then you comment this line, maybe you want to so some test or other things
```shell
 php:
     build: ./php  #指定build Dockerfile生成镜像
     image: php:5.6-fpm
     ports:
         - "9000:9000"
     volumes:
         - "$PWD/dataweb:/var/www/html"
         #- "./php/php.ini:/usr/local/etc/php/php.ini"
     networks:
         - app_net
     container_name: "compose-php"
```
after finishing your tests, you remove the `#` before this line, but if you modify some values in `php.ini` in host, the 
counterpart in container would not be changed accordingly.

* Reason:    
because the container that was started originally generated 
a default volume on the host, and the original volume was not deleted when it was re-up, resulting in the newly hung one 
not taking effect.

* Solution:    
Stop the docker container first and then delete it. Recreating a container will be fine.