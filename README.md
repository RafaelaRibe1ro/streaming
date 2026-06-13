# 🎵 Streaming API

API REST de um aplicativo de streaming de música desenvolvida como projeto da disciplina de Design Patterns e Domain-Driven Design (DDD) com Java no INFNET. A ideia foi construir algo parecido com o Spotify, aplicando os conceitos de DDD e boas práticas de desenvolvimento.

---

## Tecnologias

- Java 21
- Spring Boot 4.1.0
- Spring Data JPA
- Banco H2 (em memória)
- Lombok
- Maven

---

## Endpoints

### Contas
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/contas` | Criar conta |
| GET | `/contas/{id}` | Buscar conta |
| POST | `/contas/{id}/cartao` | Adicionar cartão de crédito |
| POST | `/contas/{id}/assinatura` | Ativar assinatura |
| DELETE | `/contas/{id}/assinatura` | Cancelar assinatura |
| POST | `/contas/{id}/favoritas/{musicaId}` | Favoritar música |
| GET | `/contas/{id}/favoritas` | Listar favoritas |

### Músicas
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/musicas` | Registrar música |
| GET | `/musicas` | Listar músicas |
| GET | `/musicas/{id}` | Buscar música |

### Playlists
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/contas/{id}/playlists` | Criar playlist |
| GET | `/contas/{id}/playlists` | Listar playlists |
| POST | `/playlists/{id}/musicas/{musicaId}` | Adicionar música à playlist |

### Transações
| Método | Rota | Descrição |
|--------|------|-----------|
| POST | `/transacoes` | Autorizar transação |
| GET | `/transacoes?contaId={id}` | Listar transações da conta |

---

## Diagrama

<img width="1433" height="3790" alt="IAM and CFMV Integration-2026-06-13-195341" src="https://github.com/user-attachments/assets/603dbd2d-22f3-4263-b7bf-e219614a3cd3" />

---

## Arquitetura

O projeto segue uma estrutura baseada em DDD com três bounded contexts:

```
dominio/
├── conta/        → Conta, CartaoCredito, PlanoAssinatura
├── musica/       → Musica, Playlist
└── transacao/    → Transacao, ServicoAntifraude, RegraAntifraude
```

O contexto de **Transação** é o domínio principal (onde ficam as regras de negócio mais importantes). **Conta** é um subdomínio de suporte e **Música** é genérico.
