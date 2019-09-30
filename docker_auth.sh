#!/usr/bin/expect
spawn gcloud ("auth", "configure-docker")
expect "Do you want to continue (Y/n)?" { send "Y\r" }