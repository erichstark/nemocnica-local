ECHO "Stopping server"
FOR /F "tokens=5 delims= " %P IN ('netstat -a -n -o ^| findstr :8080') DO TaskKill.exe /PID %P /T /F