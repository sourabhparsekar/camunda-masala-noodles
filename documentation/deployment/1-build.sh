#!/bin/sh
set -e
. ./config.sh

rm -rf $dir_name
git clone $repo_url

cd $build_path
git checkout $branch
cd -

cd $build_path
sh $build_cmd
