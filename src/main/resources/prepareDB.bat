cd /d "%~dp0"
set MYSQL_HOME=C:\Program Files\MySQL\MySQL Server 5.6
"%MYSQL_HOME%\bin\mysql" -u root -proot < tasksDB_structure_and_data.sql
pause