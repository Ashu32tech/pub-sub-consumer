@echo off
cd /d %cd%
echo -----------------------------------------------
echo %cd%
echo -----------------------------------------------
git status
git add .
set /p commitMessage=Enter Commit Message: 
git commit -m "%commitMessage%"
git push https://avinash.pocs@gmail.com@github.com
pause