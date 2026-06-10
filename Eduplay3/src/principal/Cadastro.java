package principal;

import estruturaArray.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.*;
import aula.*;
import sistemadenotas.*;
import cordenacao.RegistroContato;

public class Cadastro {

    static final String[] CURSOS = {
        "Lógica de Programação: Criando Meu Primeiro Jogo",
        "Robótica Educativa: Programando no Mundo Real",
        "Criatividade Digital com Minecraft: Criar e Explorar",
        "Segurança em Redes: Usar a Internet com Segurança"
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Array bancoDados = new Array();

        // --- Dados de teste iniciais ---
        Responsavel respTeste = new Responsavel("Jane Cristina da Silva", "321.321.234-12", 40,
                "jane@gmail.com", "Feminino", "(11) 91234-5678");
        bancoDados.inserirResponsavel(respTeste);

        Crianca criancaTeste = new Crianca("Pedro Henrique Silva", "123.123.123-12", 12,
                "pedro@gmail.com", "Masculino",
                "M-LP-1", "Matutino", CURSOS[0], respTeste);
        bancoDados.inserirCrianca(criancaTeste);

        Professor profTeste = new Professor("Carlos Alberto Souza", "456.456.456-45", 35,
                "carlos@gmail.com", "Masculino", CURSOS[0]);
        bancoDados.inserirProfessor(profTeste);

        while (true) {
            try {
                System.out.println("==============================================================================");
                System.out.println("                        ----- EDUPLAY3 — SISTEMA -----");
                System.out.println("  1)  Cadastrar Aluno e Responsável");
                System.out.println("  2)  Ver Responsáveis por Aluno");
                System.out.println("  3)  Ver Alunos");
                System.out.println("  4)  Atualizar Cadastro");
                System.out.println("  5)  Procurar Aluno");
                System.out.println("  6)  Excluir Aluno");
                System.out.println("  7)  Gerenciar Professores Voluntários");
                System.out.println("  8)  Cadastrar Aula");
                System.out.println("  9)  Registrar Frequência");
                System.out.println("  10) Relatório de Aulas e Frequência");
                System.out.println("  11) Coordenação");
                System.out.println("  12) Sistema de Notas");
                System.out.println("  0)  Sair");
                System.out.println("------------------------------------------------------------------------------");
                System.out.print("  Escolha: ");

                int opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 0:
                        System.out.println("Encerrando sistema. Até logo!");
                        scanner.close();
                        return;

                    case 1:  cadastrarAlunoResponsavel(scanner, bancoDados); break;
                    case 2:
                        System.out.println("==============================================================================");
                        System.out.println("             ----- RESPONSÁVEIS POR ALUNO -----");
                        bancoDados.exibirResponsaveisPorAluno();
                        break;
                    case 3:
                        System.out.println("==============================================================================");
                        System.out.println("                   ----- ALUNOS CADASTRADOS -----");
                        bancoDados.exibirCriancas();
                        break;
                    case 4:  menuAtualizar(scanner, bancoDados); break;
                    case 5:  menuProcurarAluno(scanner, bancoDados); break;
                    case 6:  menuExcluirAluno(scanner, bancoDados); break;
                    case 7:  menuProfessores(scanner, bancoDados); break;
                    case 8:  cadastrarAula(scanner, bancoDados); break;
                    case 9:  registrarFrequencia(scanner, bancoDados); break;
                    case 10: exibirRelatorio(bancoDados); break;
                    case 11: menuCoordenacao(scanner, bancoDados); break;
                    case 12: menuNotas(scanner, bancoDados); break;
                    default: System.out.println("Opção inválida!"); break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida! Digite um número.");
                scanner.next();
            }
        }
    }

    // ===========================================================================
    //  CASE 1 — CADASTRAR ALUNO E RESPONSÁVEL
    // ===========================================================================
    private static void cadastrarAlunoResponsavel(Scanner sc, Array bd) {
        System.out.println("==============================================================================");
        System.out.println("                    ----- CADASTRO DO RESPONSÁVEL -----");

        String nomeResp = lerNomeCompleto(sc, "Nome completo do responsável: ");
        int idadeResp = lerIdade(sc, 18, 99, "Idade do responsável (18-99): ");
        String cpfResp = lerCpfCompleto(sc, "CPF do responsável (XXX.XXX.XXX-XX): ");
        String emailResp = lerEmailCompleto(sc, "Email do responsável: ");
        String generoResp = escolherGenero(sc, "Gênero do responsável:");
        String telResp = lerTelefone(sc, "Telefone do responsável: ");

        System.out.println("==============================================================================");
        System.out.println("                     ----- CADASTRO DA CRIANÇA -----");

        String nomeCrianca = lerNomeCompleto(sc, "Nome completo da criança: ");
        int idadeCrianca = lerIdade(sc, 1, 17, "Idade da criança (1-17): ");
        String cpfCrianca = lerCpfCompleto(sc, "CPF da criança (XXX.XXX.XXX-XX): ");
        String emailCrianca = lerEmailCompleto(sc, "Email da criança: ");
        String generoCrianca = escolherGenero(sc, "Gênero da criança:");
        String curso = escolherCurso(sc);
        String turno = escolherTurno(sc);
        String turma = escolherTurma(sc, turno, curso);

        Responsavel novoResp = new Responsavel(nomeResp, cpfResp, idadeResp, emailResp, generoResp, telResp);
        boolean respOk = bd.inserirResponsavel(novoResp);

        Crianca novaCrianca = new Crianca(nomeCrianca, cpfCrianca, idadeCrianca, emailCrianca,
                generoCrianca, turma, turno, curso, novoResp);
        boolean criancaOk = bd.inserirCrianca(novaCrianca);

        if (respOk && criancaOk) {
            System.out.println("==============================================================================");
            System.out.println("✅ Aluno e Responsável cadastrados com sucesso!");
            System.out.println(novaCrianca);
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(novoResp);
        }
    }

    // ===========================================================================
    //  CASE 4 — ATUALIZAR
    // ===========================================================================
    private static void menuAtualizar(Scanner sc, Array bd) {
        System.out.println("==============================================================================");
        System.out.println("1) Atualizar Responsável   2) Atualizar Aluno");
        System.out.print("Escolha: ");
        try {
            int op = sc.nextInt(); sc.nextLine();
            if (op == 1) {
                System.out.print("ID do aluno vinculado ao responsável: ");
                int id = sc.nextInt(); sc.nextLine();
                String nome = lerNomeCompleto(sc, "Novo nome do responsável: ");
                String cpf = lerCpfCompleto(sc, "Novo CPF (XXX.XXX.XXX-XX): ");
                int idade = lerIdade(sc, 18, 99, "Nova idade (18-99): ");
                String email = lerEmailCompleto(sc, "Novo email: ");
                String genero = escolherGenero(sc, "Novo gênero:");
                String tel = lerTelefone(sc, "Novo telefone: ");
                System.out.println(bd.atualizarResponsavel(id, nome, cpf, idade, email, genero, tel)
                        ? "✅ Responsável atualizado!" : "❌ Aluno não encontrado.");
            } else if (op == 2) {
                System.out.print("ID do aluno: ");
                int id = sc.nextInt(); sc.nextLine();
                String nome = lerNomeCompleto(sc, "Novo nome do aluno: ");
                String cpf = lerCpfCompleto(sc, "Novo CPF (XXX.XXX.XXX-XX): ");
                int idade = lerIdade(sc, 1, 17, "Nova idade (1-17): ");
                String email = lerEmailCompleto(sc, "Novo email: ");
                String genero = escolherGenero(sc, "Novo gênero:");
                String curso = escolherCurso(sc);
                String turno = escolherTurno(sc);
                String turma = escolherTurma(sc, turno, curso);
                System.out.println(bd.atualizarAluno(id, nome, cpf, idade, email, genero, turma, turno, curso)
                        ? "✅ Aluno atualizado!" : "❌ Aluno não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida!"); sc.next();
        }
    }

    // ===========================================================================
    //  CASE 5 — PROCURAR ALUNO
    // ===========================================================================
    private static void menuProcurarAluno(Scanner sc, Array bd) {
        System.out.println("==============================================================================");
        System.out.println("1) Procurar por CPF   2) Procurar por ID");
        System.out.print("Escolha: ");
        try {
            int op = sc.nextInt(); sc.nextLine();
            if (op == 1) {
                System.out.print("CPF do aluno (XXX.XXX.XXX-XX): ");
                bd.buscarAlunoPorCpf(sc.nextLine().trim());
            } else if (op == 2) {
                System.out.print("ID do aluno: ");
                int id = sc.nextInt(); sc.nextLine();
                bd.buscarAlunoPorId(id);
            } else {
                System.out.println("Opção inválida!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida!"); sc.next();
        }
    }

    // ===========================================================================
    //  CASE 6 — EXCLUIR ALUNO
    // ===========================================================================
    private static void menuExcluirAluno(Scanner sc, Array bd) {
        System.out.println("==============================================================================");
        System.out.print("ID do aluno que deseja excluir: ");
        try {
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("Confirma exclusão? (S/N): ");
            String conf = sc.nextLine().trim().toUpperCase();
            if (conf.equals("S")) {
                System.out.println(bd.excluirAluno(id)
                        ? "✅ Aluno e responsável excluídos." : "❌ Aluno não encontrado.");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida!"); sc.next();
        }
    }

    // ===========================================================================
    //  CASE 7 — PROFESSORES VOLUNTÁRIOS
    // ===========================================================================
    private static void menuProfessores(Scanner sc, Array bd) {
        while (true) {
            try {
                System.out.println("==============================================================================");
                System.out.println("            ----- PROFESSORES VOLUNTÁRIOS -----");
                System.out.println("1) Cadastrar   2) Ver Todos   3) Atualizar   4) Procurar   5) Excluir   6) Voltar");
                System.out.print("Escolha: ");
                int op = sc.nextInt(); sc.nextLine();
                switch (op) {
                    case 1:
                        String nome = lerNomeCompleto(sc, "Nome completo: ");
                        int idade = lerIdade(sc, 18, 99, "Idade (18-99): ");
                        String cpf = lerCpfCompleto(sc, "CPF (XXX.XXX.XXX-XX): ");
                        String email = lerEmailCompleto(sc, "Email: ");
                        String genero = escolherGenero(sc, "Gênero:");
                        System.out.println("Especialidade (curso que ministrará):");
                        String espec = escolherCurso(sc);
                        Professor prof = new Professor(nome, cpf, idade, email, genero, espec);
                        System.out.println(bd.inserirProfessor(prof)
                                ? "✅ Professor cadastrado!" : "");
                        break;
                    case 2:
                        bd.exibirProfessores();
                        break;
                    case 3:
                        System.out.print("ID do professor: ");
                        int id3 = sc.nextInt(); sc.nextLine();
                        String n3 = lerNomeCompleto(sc, "Novo nome: ");
                        String c3 = lerCpfCompleto(sc, "Novo CPF: ");
                        int i3 = lerIdade(sc, 18, 99, "Nova idade: ");
                        String e3 = lerEmailCompleto(sc, "Novo email: ");
                        String g3 = escolherGenero(sc, "Novo gênero:");
                        System.out.println("Nova especialidade:");
                        String es3 = escolherCurso(sc);
                        System.out.println(bd.atualizarProfessor(id3, n3, c3, i3, e3, g3, es3)
                                ? "✅ Professor atualizado!" : "❌ Não encontrado.");
                        break;
                    case 4:
                        System.out.println("1) Por CPF   2) Por ID");
                        System.out.print("Escolha: ");
                        int opB = sc.nextInt(); sc.nextLine();
                        if (opB == 1) {
                            System.out.print("CPF: "); bd.buscarProfessorPorCpf(sc.nextLine().trim());
                        } else {
                            System.out.print("ID: "); bd.buscarProfessorPorId(sc.nextInt()); sc.nextLine();
                        }
                        break;
                    case 5:
                        System.out.print("ID do professor a excluir: ");
                        int id5 = sc.nextInt(); sc.nextLine();
                        System.out.println(bd.excluirProfessor(id5)
                                ? "✅ Professor excluído." : "❌ Não encontrado.");
                        break;
                    case 6: return;
                    default: System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida!"); sc.next();
            }
        }
    }

    // ===========================================================================
    //  CASE 8 — CADASTRAR AULA
    // ===========================================================================
    private static void cadastrarAula(Scanner sc, Array bd) {
        if (bd.getContProfessores() == 0) {
            System.out.println("⚠️  Nenhum professor cadastrado. Cadastre um professor primeiro!");
            return;
        }
        System.out.println("==============================================================================");
        System.out.println("                     ----- CADASTRAR AULA -----");
        System.out.println("Selecione o professor voluntário:");
        for (int i = 0; i < bd.getContProfessores(); i++) {
            System.out.println("  " + (i + 1) + ") " + bd.getProfessores()[i].getNome()
                    + " — " + bd.getProfessores()[i].getEspecialidade());
        }
        System.out.print("Escolha: ");
        try {
            int idx = sc.nextInt() - 1; sc.nextLine();
            if (idx < 0 || idx >= bd.getContProfessores()) {
                System.out.println("Seleção inválida!"); return;
            }
            Professor prof = bd.getProfessores()[idx];
            System.out.println("Curso desta aula:");
            String curso = escolherCurso(sc);
            System.out.print("Tema da aula: ");
            String tema = sc.nextLine().trim();
            System.out.println("Tipo:  1) Teórica   2) Prática");
            System.out.print("Escolha: ");
            int tipo = sc.nextInt(); sc.nextLine();
            if (tipo == 1) {
                System.out.print("Conteúdo teórico: ");
                bd.inserirAula(new AulaTeorica(tema, prof, curso, sc.nextLine().trim()));
                System.out.println("✅ Aula Teórica cadastrada!");
            } else if (tipo == 2) {
                System.out.print("Atividade prática: ");
                bd.inserirAula(new AulaPratica(tema, prof, curso, sc.nextLine().trim()));
                System.out.println("✅ Aula Prática cadastrada!");
            } else {
                System.out.println("Tipo inválido.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida!"); sc.next();
        }
    }

    // ===========================================================================
    //  CASE 9 — REGISTRAR FREQUÊNCIA
    // ===========================================================================
    private static void registrarFrequencia(Scanner sc, Array bd) {
        if (bd.getContAulas() == 0) {
            System.out.println("⚠️  Nenhuma aula cadastrada. Cadastre uma aula primeiro!"); return;
        }
        System.out.println("==============================================================================");
        System.out.println("                    ----- REGISTRAR FREQUÊNCIA -----");
        System.out.println("Selecione a aula:");
        for (int i = 0; i < bd.getContAulas(); i++) {
            Aula a = bd.getAulas()[i];
            System.out.println("  " + (i + 1) + ") [" + a.getTipo() + "] " + a.getTema() + " — " + a.getCurso());
        }
        System.out.print("Escolha: ");
        try {
            int idx = sc.nextInt() - 1; sc.nextLine();
            if (idx < 0 || idx >= bd.getContAulas()) {
                System.out.println("Seleção inválida!"); return;
            }
            Aula aulaSel = bd.getAulas()[idx];
            boolean ehPratica = aulaSel instanceof AulaPratica;

            int count = 0;
            for (int i = 0; i < bd.getContCriancas(); i++) {
                if (bd.getCriancas()[i].getCurso().equals(aulaSel.getCurso())) count++;
            }
            if (count == 0) {
                System.out.println("Nenhum aluno matriculado no curso: " + aulaSel.getCurso()); return;
            }
            System.out.println("Curso: " + aulaSel.getCurso() + " | Aula: " + aulaSel.getTema());
            System.out.println("------------------------------------------------------------------------------");
            for (int i = 0; i < bd.getContCriancas(); i++) {
                Crianca aluno = bd.getCriancas()[i];
                if (!aluno.getCurso().equals(aulaSel.getCurso())) continue;
                if (aluno.isEvasao()) {
                    System.out.println("⚠️  " + aluno.getNome() + " está EVADIDO — frequência ignorada.");
                    continue;
                }
                String resp;
                do {
                    System.out.print(aluno.getNome() + " (ID: " + aluno.getMatricula() + ") — Presente? (S/N): ");
                    resp = sc.nextLine().trim().toUpperCase();
                    if (!resp.equals("S") && !resp.equals("N"))
                        System.out.println("Digite S ou N.");
                } while (!resp.equals("S") && !resp.equals("N"));
                aluno.registrarPresenca(resp.equals("S"), ehPratica);
            }
            System.out.println("✅ Frequência registrada com sucesso!");
        } catch (InputMismatchException e) {
            System.out.println("Opção inválida!"); sc.next();
        }
    }

    // ===========================================================================
    //  CASE 10 — RELATÓRIO GERAL
    // ===========================================================================
    private static void exibirRelatorio(Array bd) {
        System.out.println("==============================================================================");
        System.out.println("              ----- RELATÓRIO DE AULAS E FREQUÊNCIA -----");
        System.out.println("\n--- AULAS CADASTRADAS ---");
        bd.exibirAulas();
        System.out.println("\n--- FREQUÊNCIA DOS ALUNOS ---");
        bd.exibirCriancas();
        System.out.println();
        bd.exibirRelatorioAulasPorCurso(CURSOS);
    }

    // ===========================================================================
    //  CASE 11 — COORDENAÇÃO
    // ===========================================================================
    private static void menuCoordenacao(Scanner sc, Array bd) {
        while (true) {
            try {
                System.out.println("==============================================================================");
                System.out.println("                       ----- COORDENAÇÃO -----");
                System.out.println("1) Registrar Ligação com Responsável");
                System.out.println("2) Registrar Envio de E-mail");
                System.out.println("3) Registrar Observação sobre Aluno");
                System.out.println("4) Marcar Aluno como Evadido");
                System.out.println("5) Ver Histórico de Contatos de um Aluno");
                System.out.println("6) Ver Todos os Registros");
                System.out.println("7) Ver Relatório de Alunos Evadidos");
                System.out.println("8) Ver Alunos em Situação de Risco");
                System.out.println("9) Voltar");
                System.out.print("Escolha: ");
                int op = sc.nextInt(); sc.nextLine();

                switch (op) {
                    case 1: registrarContato(sc, bd, "Ligação"); break;
                    case 2: registrarContato(sc, bd, "Email"); break;
                    case 3: registrarContato(sc, bd, "Observação"); break;
                    case 4:
                        System.out.print("ID do aluno a marcar como evadido: ");
                        int idEv = sc.nextInt(); sc.nextLine();
                        Crianca evadido = bd.encontrarAlunoPorId(idEv);
                        if (evadido == null) {
                            System.out.println("❌ Aluno não encontrado.");
                        } else if (evadido.isEvasao()) {
                            System.out.println("⚠️  Aluno já estava marcado como evadido.");
                        } else {
                            evadido.setEvasao(true);
                            System.out.print("Descreva o motivo da evasão: ");
                            String motivo = sc.nextLine().trim();
                            bd.inserirRegistro(new RegistroContato("Evasão", motivo,
                                    evadido.getNome(), evadido.getMatricula()));
                            System.out.println("✅ Aluno marcado como evadido e registro salvo.");
                        }
                        break;
                    case 5:
                        System.out.print("ID do aluno: ");
                        int idH = sc.nextInt(); sc.nextLine();
                        bd.exibirRegistrosPorAluno(idH);
                        break;
                    case 6:
                        bd.exibirTodosRegistros();
                        break;
                    case 7:
                        System.out.println("==============================================================================");
                        System.out.println("                  ----- ALUNOS EVADIDOS -----");
                        bd.exibirAlunosEvadidos();
                        break;
                    case 8:
                        bd.exibirAlunosEmRisco();
                        break;
                    case 9: return;
                    default: System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida!"); sc.next();
            }
        }
    }

    private static void registrarContato(Scanner sc, Array bd, String tipo) {
        System.out.print("ID do aluno: ");
        try {
            int id = sc.nextInt(); sc.nextLine();
            Crianca aluno = bd.encontrarAlunoPorId(id);
            if (aluno == null) { System.out.println("❌ Aluno não encontrado."); return; }
            System.out.print("Descrição do " + tipo + ": ");
            String desc = sc.nextLine().trim();
            bd.inserirRegistro(new RegistroContato(tipo, desc, aluno.getNome(), aluno.getMatricula()));
            System.out.println("✅ Registro de " + tipo + " salvo com data/hora automática!");
        } catch (InputMismatchException e) {
            System.out.println("ID inválido!"); sc.next();
        }
    }

    // ===========================================================================
    //  CASE 12 — SISTEMA DE NOTAS
    // ===========================================================================
    private static void menuNotas(Scanner sc, Array bd) {
        while (true) {
            try {
                System.out.println("==============================================================================");
                System.out.println("                    ----- SISTEMA DE NOTAS -----");
                System.out.println("1) Cadastrar Prova");
                System.out.println("2) Lançar Nota para Aluno");
                System.out.println("3) Ver Boletim Individual");
                System.out.println("4) Ver Boletim Completo");
                System.out.println("5) Voltar");
                System.out.print("Escolha: ");
                int op = sc.nextInt(); sc.nextLine();

                switch (op) {
                    case 1:
                        System.out.print("Nome da prova (ex: Prova 1, Prova Final): ");
                        String nomeProva = sc.nextLine().trim();
                        System.out.println("Curso da prova:");
                        String cursoProva = escolherCurso(sc);
                        Prova prova = new Prova(nomeProva, cursoProva);
                        bd.inserirProva(prova);
                        System.out.println("✅ Prova cadastrada!");
                        System.out.println(prova);
                        break;

                    case 2:
                        if (bd.getContProvas() == 0) {
                            System.out.println("⚠️  Nenhuma prova cadastrada. Cadastre uma prova primeiro!"); break;
                        }
                        System.out.println("Provas disponíveis:");
                        for (int i = 0; i < bd.getContProvas(); i++) {
                            System.out.println("  " + (i + 1) + ") "
                                    + bd.getProvas()[i].getNomeProva()
                                    + " — " + bd.getProvas()[i].getCurso());
                        }
                        System.out.print("Selecione a prova: ");
                        int idxP = sc.nextInt() - 1; sc.nextLine();
                        if (idxP < 0 || idxP >= bd.getContProvas()) {
                            System.out.println("Seleção inválida!"); break;
                        }
                        Prova provaSel = bd.getProvas()[idxP];

                        // Alunos do curso da prova
                        int totalAlunos = 0;
                        for (int i = 0; i < bd.getContCriancas(); i++) {
                            if (bd.getCriancas()[i].getCurso().equals(provaSel.getCurso())) totalAlunos++;
                        }
                        if (totalAlunos == 0) {
                            System.out.println("Nenhum aluno no curso: " + provaSel.getCurso()); break;
                        }
                        System.out.println("Lançando notas — Prova: " + provaSel.getNomeProva()
                                + " | Curso: " + provaSel.getCurso());
                        System.out.println("------------------------------------------------------------------------------");
                        for (int i = 0; i < bd.getContCriancas(); i++) {
                            Crianca aluno = bd.getCriancas()[i];
                            if (!aluno.getCurso().equals(provaSel.getCurso())) continue;
                            if (aluno.isEvasao()) {
                                System.out.println("⚠️  " + aluno.getNome() + " está evadido — nota ignorada.");
                                continue;
                            }
                            double valor = -1;
                            do {
                                System.out.print("Nota de " + aluno.getNome()
                                        + " (ID: " + aluno.getMatricula() + ") [0.0 – 10.0]: ");
                                try {
                                    valor = sc.nextDouble(); sc.nextLine();
                                    if (valor < 0.0 || valor > 10.0)
                                        System.out.println("Nota inválida! Digite entre 0.0 e 10.0.");
                                } catch (InputMismatchException ex) {
                                    System.out.println("Valor inválido!"); sc.next(); valor = -1;
                                }
                            } while (valor < 0.0 || valor > 10.0);

                            Nota nota = new Nota(provaSel.getId(), provaSel.getNomeProva(),
                                    provaSel.getCurso(), aluno.getMatricula(), aluno.getNome(), valor);
                            bd.inserirNota(nota);
                        }
                        System.out.println("✅ Notas lançadas com sucesso!");
                        break;

                    case 3:
                        System.out.print("ID do aluno: ");
                        int idBol = sc.nextInt(); sc.nextLine();
                        System.out.println("==============================================================================");
                        bd.exibirBoletimAluno(idBol);
                        break;

                    case 4:
                        System.out.println("==============================================================================");
                        System.out.println("                  ----- BOLETIM COMPLETO -----");
                        bd.exibirBoletimCompleto();
                        break;

                    case 5: return;
                    default: System.out.println("Opção inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Opção inválida!"); sc.next();
            }
        }
    }

    // ===========================================================================
    //  MÉTODOS AUXILIARES DE VALIDAÇÃO
    // ===========================================================================

    /** Lê nome completo com mínimo de 2 palavras e apenas letras/espaços */
    public static String lerNomeCompleto(Scanner sc, String msg) {
        String nome;
        do {
            System.out.print(msg);
            nome = sc.nextLine().trim();
            if (nome.isBlank()) {
                System.out.println("❌ Nome não pode ser vazio."); continue;
            }
            if (!nome.matches("[a-zA-ZÀ-ÿ\\s]+")) {
                System.out.println("❌ Nome inválido! Não use números ou caracteres especiais."); continue;
            }
            String[] partes = nome.split("\\s+");
            if (partes.length < 2) {
                System.out.println("❌ Digite o nome completo (nome e sobrenome)."); continue;
            }
            break;
        } while (true);
        return nome;
    }

    /** Lê idade inteira dentro de [min, max] */
    public static int lerIdade(Scanner sc, int min, int max, String msg) {
        int idade = -1;
        do {
            System.out.print(msg);
            try {
                idade = sc.nextInt(); sc.nextLine();
                if (idade < min || idade > max)
                    System.out.println("❌ Idade inválida! Digite entre " + min + " e " + max + ".");
            } catch (InputMismatchException e) {
                System.out.println("❌ Valor inválido! Digite um número."); sc.next();
            }
        } while (idade < min || idade > max);
        return idade;
    }

    /** Lê telefone brasileiro (10 ou 11 dígitos) */
    public static String lerTelefone(Scanner sc, String msg) {
        String tel;
        do {
            System.out.print(msg);
            tel = sc.nextLine().trim();
            if (!telefoneValido(tel))
                System.out.println("❌ Telefone inválido! Use (XX) XXXXX-XXXX (celular) ou (XX) XXXX-XXXX (fixo).");
        } while (!telefoneValido(tel));
        return tel;
    }

    public static boolean telefoneValido(String tel) {
        String digitos = tel.replaceAll("[^\\d]", "");
        return digitos.length() == 10 || digitos.length() == 11;
    }

    /** Lê e-mail com regex */
    public static String lerEmailCompleto(Scanner sc, String msg) {
        String email;
        do {
            System.out.print(msg);
            email = sc.nextLine().trim();
            if (!emailValido(email))
                System.out.println("❌ Email inválido! Use o formato nome@dominio.com");
        } while (!emailValido(email));
        return email;
    }

    public static boolean emailValido(String email) {
        return email.matches("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");
    }

    /** CPF no formato XXX.XXX.XXX-XX */
    public static String lerCpfCompleto(Scanner sc, String msg) {
        String cpf;
        do {
            System.out.print(msg);
            cpf = sc.nextLine().trim();
            if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))
                System.out.println("❌ CPF inválido! Use o formato XXX.XXX.XXX-XX.");
        } while (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"));
        return cpf;
    }

    /** Menu de gênero */
    public static String escolherGenero(Scanner sc, String msg) {
        int op = -1;
        do {
            System.out.println(msg);
            System.out.println("  1) Masculino   2) Feminino   3) Outro");
            System.out.print("Opção: ");
            try {
                op = sc.nextInt(); sc.nextLine();
                if (op < 1 || op > 3) System.out.println("❌ Opção inválida!");
            } catch (InputMismatchException e) {
                System.out.println("❌ Opção inválida!"); sc.next();
            }
        } while (op < 1 || op > 3);
        return op == 1 ? "Masculino" : op == 2 ? "Feminino" : "Outro";
    }

    /** Menu de curso */
    public static String escolherCurso(Scanner sc) {
        int op = -1;
        do {
            System.out.println("Escolha o curso:");
            for (int i = 0; i < CURSOS.length; i++)
                System.out.println("  " + (i + 1) + ") " + CURSOS[i]);
            System.out.print("Opção: ");
            try {
                op = sc.nextInt(); sc.nextLine();
                if (op < 1 || op > CURSOS.length) System.out.println("❌ Opção inválida!");
            } catch (InputMismatchException e) {
                System.out.println("❌ Opção inválida!"); sc.next();
            }
        } while (op < 1 || op > CURSOS.length);
        return CURSOS[op - 1];
    }

    /** Menu de turno */
    public static String escolherTurno(Scanner sc) {
        int op = -1;
        do {
            System.out.println("Escolha o turno:  1) Matutino   2) Vespertino   3) Noturno");
            System.out.print("Opção: ");
            try {
                op = sc.nextInt(); sc.nextLine();
                if (op < 1 || op > 3) System.out.println("❌ Opção inválida!");
            } catch (InputMismatchException e) {
                System.out.println("❌ Opção inválida!"); sc.next();
            }
        } while (op < 1 || op > 3);
        String[] turnos = {"Matutino", "Vespertino", "Noturno"};
        return turnos[op - 1];
    }

    /** Gera turma baseada no turno e curso */
    public static String escolherTurma(Scanner sc, String turno, String curso) {
        String pT = turno.equals("Matutino") ? "M" : turno.equals("Vespertino") ? "V" : "N";
        String pC = curso.startsWith("Lógica") ? "LP"
                : curso.startsWith("Robótica") ? "RE"
                : curso.startsWith("Criatividade") ? "MC" : "SR";
        String[] turmas = {pT + "-" + pC + "-1", pT + "-" + pC + "-2", pT + "-" + pC + "-3"};
        int op = -1;
        do {
            System.out.println("Escolha a turma (" + turno + " | " + curso + "):");
            for (int i = 0; i < turmas.length; i++)
                System.out.println("  " + (i + 1) + ") " + turmas[i]);
            System.out.print("Opção: ");
            try {
                op = sc.nextInt(); sc.nextLine();
                if (op < 1 || op > 3) System.out.println("❌ Opção inválida!");
            } catch (InputMismatchException e) {
                System.out.println("❌ Opção inválida!"); sc.next();
            }
        } while (op < 1 || op > 3);
        System.out.println("Turma atribuída: " + turmas[op - 1]);
        return turmas[op - 1];
    }

    // Mantidos para compatibilidade
    public static boolean nomeValido(String nome) {
        return nome.matches("[a-zA-ZÀ-ÿ\\s]+");
    }
    public static boolean cpfFormatoValido(String cpf) {
        return cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }
}