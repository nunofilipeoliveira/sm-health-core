#!/bin/bash

# Script para build da imagem Docker do sm-health-core
# Uso: ./build-docker.sh [ambiente]

set -e

# Cores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Ambiente padrão
ENV=${1:-prod}

echo -e "${GREEN}=== Build da Imagem Docker - sm-health-core ===${NC}"
echo -e "${YELLOW}Ambiente: ${ENV}${NC}"

# Verificar se Docker está instalado
if ! command -v docker &> /dev/null; then
    echo -e "${RED}Erro: Docker não está instalado${NC}"
    exit 1
fi

# Build da imagem
echo -e "${GREEN}Iniciando build da imagem...${NC}"
docker build -t sm-health-core:${ENV} .

# Verificar se o build foi bem-sucedido
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Imagem criada com sucesso!${NC}"
    echo -e "${GREEN}Nome: sm-health-core:${ENV}${NC}"
    
    # Mostrar tamanho da imagem
    echo -e "${YELLOW}Tamanho da imagem:${NC}"
    docker images sm-health-core:${ENV}
    
    echo -e "\n${GREEN}Para executar:${NC}"
    echo -e "  docker run -d -p 8080:8080 --name sm-health-core sm-health-core:${ENV}"
    echo -e "\n${GREEN}Ou usando docker-compose:${NC}"
    echo -e "  docker-compose up -d"
else
    echo -e "${RED}✗ Erro ao criar a imagem${NC}"
    exit 1
fi
