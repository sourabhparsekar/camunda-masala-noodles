. ./config.sh
set -x
set -e

cd $dir_name
sudo docker build . -t $prj_name

if [ "$(sudo docker ps -q -f name=$prj_name)" ]; then
        echo "Stopping container"
        sudo docker stop $prj_name
        echo "remove container"
        sudo docker rm $prj_name
else
        echo "Container Already Stopped"
        if [ "$(sudo docker ps -aq -f status=exited -f name=$prj_name)" ]; then
                echo "remove stopped container"
                sudo docker rm $prj_name
        fi
fi

echo "run container"
sudo docker run -d --name $prj_name -p $prj_port:$prj_port $prj_name

