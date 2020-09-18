#!/usr/bin/env bash
set -e
# Install venv for python3
which python3.6 || { sudo add-apt-repository ppa:deadsnakes/ppa; sudo apt-get update; sudo apt-get install python3.6 python3-pip python3.6-venv; python3.6 --version; }
python3.6 -m venv env

sed -i 's/$1/${1:-}/' env/bin/activate # Fix deactivation bug '$1 unbound variable'
source env/bin/activate
python3.6 -m pip install --upgrade pip
python3.6 -m pip install -r requirements.txt

# Run tests
python3.6 run_test.py

TEST_STATUS=$?

python3.6 -m pip freeze > requirements.txt
python3.6 -m pip uninstall -r requirements.txt -y
deactivate
rm -rf env/

if [ $TEST_STATUS -ne 0 ]
then
    exit 1
fi
