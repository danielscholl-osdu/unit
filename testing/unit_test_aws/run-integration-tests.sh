# Copyright © 2020 Amazon Web Services
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      http:#www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#!/usr/bin/env bash

# Install venv for python3
which apt-get && sudo apt-get install python3 python3-pip python3-venv || echo "Not Ubuntu, skipping"

python3 -m venv env
# sed -i 's/$1/${1:-}/' env/bin/activate # Fix deactivation bug '$1 unbound variable'
source env/bin/activate
python3 -m pip install --upgrade pip
python3 -m pip install -r requirements.txt

# Run tests
echo ***RUNNING UNIT API V2 TESTS***
python3 run_test_api_v2.py
echo ***FINISHED UNIT API V2 TESTS***
echo 
echo ***RUNNING UNIT API V3 TESTS***
python3 run_test_api_v3.py
echo ***FINISHED UNIT API V3 TESTS***

TEST_STATUS=$?

# python3 -m pip freeze > requirements.txt
python3 -m pip uninstall -r requirements.txt -y
deactivate
rm -rf env/

if [ $TEST_STATUS -ne 0 ]
then
    exit 1
fi
