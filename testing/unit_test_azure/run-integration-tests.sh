#!/usr/bin/env bash
set -e

# Install venv for python3  --> No need to install as base image has python3.

# which apt-get && sudo apt-get install -y python3 python3-pip python3-venv || echo "Not Ubuntu, skipping"
# which yum && sudo yum install -y python3 python3-pip python3-venv || echo "Not RHEL, skipping"

python3 -m venv env

sed -i 's/$1/${1:-}/' env/bin/activate # Fix deactivation bug '$1 unbound variable'
source env/bin/activate
python3 -m pip install --upgrade pip
python3 -m pip install -r requirements.txt

# Run v2 Tests
export API_VER="v2"
echo ***RUNNING UNIT API $API_VER TESTS***
python3 run_test.py
TEST_STATUS_V2=$?
echo ***FINISHED UNIT API $API_VER TESTS***
echo ""

# Run v3 Tests
export API_VER="v3"
echo ***RUNNING UNIT API $API_VER TESTS***
python3 run_test.py
TEST_STATUS_V3=$?
echo ***FINISHED UNIT API $API_VER TESTS***

echo "-------------------------------"
echo "TEST_ERRORS_V2: $TEST_STATUS_V2"
echo "TEST_ERRORS_V3: $TEST_STATUS_V3"
echo "-------------------------------"

python3 -m pip freeze > requirements.txt
python3 -m pip uninstall -r requirements.txt -y
deactivate
rm -rf env/

if [ $TEST_STATUS_V2 -ne 0 ] || [ $TEST_STATUS_V3 -ne 0 ]
then
    exit 1
fi
