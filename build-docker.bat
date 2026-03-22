@echo off
REM Script para build da imagem Docker do sm-health-core
REM Uso: build-docker.bat [ambiente]

setlocal enabledelayedexpansion

REM Ambiente padrão
set ENV=%1
if "%ENV%"=="" set ENV=prod

echo === Build da Imagem Docker - sm-health-core ===
echo Ambiente: %ENV%

REM Verificar se Docker está instalado
docker --version >nul 2>&1
if errorlevel 1 (
    echo Erro: Docker não está instalado
    exit /b 1
)

REM Build da imagem
echo Iniciando build da imagem...
docker build -t sm-health-core:%ENV% .

REM Verificar se o build foi bem-sucedido
if errorlevel 1 (
    echo Erro ao criar a imagem
    exit /b 1
)

echo.
echo ✓ Imagem criada com sucesso!
echo Nome: sm-health-core:%ENV%
echo.
echo Tamanho da imagem:
docker images sm-health-core:%ENV%
echo.
echo Para executar:
echo   docker run -d -p 8080:8080 --name sm-health-core sm-health-core:%ENV%
echo.
echo Ou usando docker-compose:
echo   docker-compose up -d

endlocal
