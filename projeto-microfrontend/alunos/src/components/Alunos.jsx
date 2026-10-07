import './Alunos.css'

const alunos = [
  {
    id: 1,
    nome: 'João Silva',
    matricula: '20261001',
    presenca: 92,
  },
  {
    id: 2,
    nome: 'Maria Oliveira',
    matricula: '20261002',
    presenca: 87,
  },
  {
    id: 3,
    nome: 'Pedro Santos',
    matricula: '20261003',
    presenca: 74,
  },
]

function Alunos() {
  return (
    <div className="alunos-container">
      <div className="alunos-header">
        <div>
          <h2>Alunos</h2>
          <p>Visualize os alunos cadastrados no sistema.</p>
        </div>

        <span className="total-alunos">
          {alunos.length} alunos
        </span>
      </div>

      <div className="alunos-grid">
        {alunos.map((aluno) => (
          <div className="aluno-card" key={aluno.id}>
            <div className="aluno-avatar">
              {aluno.nome.charAt(0)}
            </div>

            <div className="aluno-info">
              <h3>{aluno.nome}</h3>
              <span>Matrícula: {aluno.matricula}</span>
            </div>

            <div className="presenca">
              <span>Presença</span>
              <strong className={aluno.presenca < 75 ? 'baixa' : ''}>
                {aluno.presenca}%
              </strong>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default Alunos