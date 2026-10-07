import { useState } from 'react'
import './RegistroPresenca.css'

const alunosIniciais = [
  {
    id: 1,
    nome: 'João Silva',
    matricula: '20261001',
    presente: true,
  },
  {
    id: 2,
    nome: 'Maria Oliveira',
    matricula: '20261002',
    presente: true,
  },
  {
    id: 3,
    nome: 'Pedro Santos',
    matricula: '20261003',
    presente: false,
  },
]

function RegistroPresenca({ aula, voltar }) {
  const [alunos, setAlunos] = useState(alunosIniciais)
  const [salvo, setSalvo] = useState(false)

  const alterarPresenca = (id) => {
    setAlunos((alunosAtuais) =>
      alunosAtuais.map((aluno) =>
        aluno.id === id
          ? { ...aluno, presente: !aluno.presente }
          : aluno
      )
    )

    setSalvo(false)
  }

  const salvarChamada = () => {
    setSalvo(true)
    voltar()
  }

  const presentes = alunos.filter((aluno) => aluno.presente).length
  const ausentes = alunos.length - presentes

  return (
    <div className="registro-container">
      <div className="registro-header">
        <div>
          <button className="voltar-button" onClick={voltar}>
            ← Voltar para chamadas
          </button>

          <h2>Registro de Presença</h2>

          <p>
            {aula?.disciplina || 'Disciplina'} •{' '}
            {aula?.professor || 'Professor'}
          </p>
        </div>
      </div>

      <div className="aula-resumo">
        <div>
          <span>Disciplina</span>
          <strong>{aula?.disciplina || '—'}</strong>
        </div>

        <div>
          <span>Professor</span>
          <strong>{aula?.professor || '—'}</strong>
        </div>

        <div>
          <span>Data</span>
          <strong>{aula?.data || '—'}</strong>
        </div>

        <div>
          <span>Horário</span>
          <strong>{aula?.horario || '—'}</strong>
        </div>
      </div>

      <div className="presenca-resumo">
        <div className="resumo-card">
          <span>Total de alunos</span>
          <strong>{alunos.length}</strong>
        </div>

        <div className="resumo-card presente">
          <span>Presentes</span>
          <strong>{presentes}</strong>
        </div>

        <div className="resumo-card ausente">
          <span>Ausentes</span>
          <strong>{ausentes}</strong>
        </div>
      </div>

      <div className="lista-presenca">
        <div className="lista-header">
          <span>Aluno</span>
          <span>Matrícula</span>
          <span>Status</span>
        </div>

        {alunos.map((aluno) => (
          <div className="aluno-presenca" key={aluno.id}>
            <div className="aluno-dados">
              <div className="aluno-avatar">
                {aluno.nome.charAt(0)}
              </div>

              <strong>{aluno.nome}</strong>
            </div>

            <span className="matricula">
              {aluno.matricula}
            </span>

            <button
              className={`presenca-button ${
                aluno.presente ? 'presente' : 'ausente'
              }`}
              onClick={() => alterarPresenca(aluno.id)}
            >
              {aluno.presente ? '✓ Presente' : '○ Ausente'}
            </button>
          </div>
        ))}
      </div>

      <div className="registro-footer">
        {salvo && (
          <span className="mensagem-sucesso">
            ✓ Chamada salva com sucesso!
          </span>
        )}

        <div className="footer-acoes">
          <button className="cancelar-button" onClick={voltar}>
            Cancelar
          </button>

          <button
            className="salvar-button"
            onClick={salvarChamada}
          >
            Salvar chamada
          </button>
        </div>
      </div>
    </div>
  )
}

export default RegistroPresenca