# Docker - sm-health-core

Este documento descreve como criar e executar uma imagem Docker do projeto sm-health-core.

## Pré-requisitos

- Docker instalado na sua máquina
- Docker Compose (opcional, para usar docker-compose)

## Criar a Imagem Docker

### Opção 1: Usando Docker diretamente

```bash
# Construir a imagem (ambiente de produção)
docker build -t sm-health-core:prod .

# Executar o container
docker run -d -p 8080:8080 --name sm-health-core sm-health-core:prod
```

### Opção 2: Usando Docker Compose

```bash
# Construir e executar (produção com banco de dados)
docker-compose up -d

# Construir e executar (desenvolvimento)
docker-compose -f docker-compose.yml -f docker-compose.dev.yml up -d

# Construir e executar (produção)
docker-compose -f docker-compose.yml -f docker-compose.prod.yml up -d
```

### Opção 3: Usando Scripts de Build

#### Windows (PowerShell/CMD)
```bash
# Build para produção
build-docker.bat prod

# Build para desenvolvimento
build-docker.bat dev
```

#### Linux/Mac
```bash
# Build para produção
./build-docker.sh prod

# Build para desenvolvimento
./build-docker.sh dev
```

## Acessar a Aplicação

Após executar o container, a aplicação estará disponível em:
- http://localhost:8080

## Comandos Úteis

### Gerenciar Containers

```bash
# Listar containers em execução
docker ps

# Parar o container
docker stop sm-health-core

# Iniciar o container
docker start sm-health-core

# Reiniciar o container
docker restart sm-health-core

# Remover o container
docker rm sm-health-core
```

### Gerenciar Imagens

```bash
# Listar imagens
docker images

# Remover a imagem
docker rmi sm-health-core:prod
```

### Logs

```bash
# Ver logs do container
docker logs sm-health-core

# Acompanhar logs em tempo real
docker logs -f sm-health-core
```

## Configurações Avançadas

### Variáveis de Ambiente

O projeto suporta as seguintes variáveis de ambiente:

- `SPRING_PROFILES_ACTIVE`: Perfil do Spring (dev, prod)
- `SPRING_DATASOURCE_URL`: URL do banco de dados
- `SPRING_DATASOURCE_USERNAME`: Usuário do banco de dados
- `SPRING_DATASOURCE_PASSWORD`: Senha do banco de dados
- `SERVER_PORT`: Porta do servidor (padrão: 8080)

### Porta Personalizada

Para usar uma porta diferente de 8080:

```bash
docker run -d -p 9090:8080 --name sm-health-core sm-health-core:prod
```

Ou modifique o `docker-compose.yml`:

```yaml
ports:
  - "9090:8080"
```

### Persistência de Dados

Para persistir logs e dados:

```bash
docker run -d -p 8080:8080 -v /caminho/local:/app/logs --name sm-health-core sm-health-core:prod
```

## Estrutura dos Arquivos Docker

- `Dockerfile` - Define a imagem Docker multi-stage build
- `.dockerignore` - Arquivos excluídos do contexto de build
- `docker-compose.yml` - Configuração base para Docker Compose
- `docker-compose.dev.yml` - Configuração para ambiente de desenvolvimento
- `docker-compose.prod.yml` - Configuração para ambiente de produção
- `docker-compose.override.yml` - Exemplo de configuração personalizada
- `.env.example` - Exemplo de variáveis de ambiente
- `build-docker.sh` - Script de build para Linux/Mac
- `build-docker.bat` - Script de build para Windows

## Troubleshooting

### Erro ao construir a imagem

Verifique se todos os arquivos necessários estão presentes:
```bash
ls -la pom.xml src/
```

### Container não inicia

Verifique os logs:
```bash
docker logs sm-health-core
```

### Porta já em uso

Verifique qual processo está usando a porta 8080:
```bash
# Windows
netstat -ano | findstr :8080

# Linux/Mac
lsof -i :8080
```

### Erro de conexão com banco de dados

Verifique se o container do banco de dados está rodando:
```bash
docker ps | grep db
```

## Notas

- A imagem utiliza Eclipse Temurin 17 JRE Alpine para execução (leve)
- O build é feito em duas stages para reduzir o tamanho da imagem final
- A aplicação Spring Boot é executada como usuário não-root (segurança)
- Health check configurado para monitorar a saúde da aplicação
- Suporte a múltiplos perfis (dev, prod)
- Banco de dados MariaDB configurado no docker-compose
