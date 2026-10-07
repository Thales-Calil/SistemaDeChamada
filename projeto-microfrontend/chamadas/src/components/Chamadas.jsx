import { lazy, Suspense, useState } from 'react'
import './Chamadas.css'

const RegistroPresenca = lazy(
  () => import('registroPresenca/RegistroPresenca')
)

const aulasIniciais = [
  {
    id: 1,
    disciplina: 'Arquitetura de Software',
    professor: 'Carlos Eduardo',
    data: '06/10/2026',
    horario: '19:00',
    status: 'Em andamento',
  },
  {
    id: 2,
    disciplina: 'Big Data',
    professor: 'Mariana Silva',
    data: '06/10/2026',
    horario: '20:40',
    status: 'Agendada',
  },
  {
    id: 3,
    disciplina: 'Reúso de Software',
    professor: 'Rafael Santos',
    data: '07/10/2026',
    horario: '19:00',
    status: 'Agendada',
  },
]

function Chamadas() {
  const [aulas, setAulas] = useState(aulasIniciais)
  const [aulaSelecionada, setAulaSelecionada] = useState(null)

  const abrirRegistro = (aula) => {
    setAulaSelecionada(aula)
  }

  const voltar = () => {
    setAulaSelecionada(null)
  }

  if (aulaSelecionada) {
    return (
      <Suspense fallback={<p>Carregando registro de presença...</p>}>
        <RegistroPresenca
          aula={aulaSelecionada}
          voltar={voltar}
        />
      </Suspense>
    )
  }

  return (
    <div className="chamadas-container">
      <div className="chamadas-header">
        <div>
          <h2>Chamadas</h2>
          <p>Gerencie a presença dos alunos nas aulas.</p>
        </div>

        <span className="total-aulas">
          {aulas.length} aulas
        </span>
      </div>

      <div className="chamadas-list">
        {aulas.map((aula) => (
          <div className="aula-card" key={aula.id}>
            <div className="aula-data">
              <span className="dia">
                {aula.data.split('/')[0]}
              </span>

              <span className="mes">
                {aula.data.split('/')[1]}
              </span>
            </div>

            <div className="aula-info">
              <h3>{aula.disciplina}</h3>

              <p>
                Professor: <strong>{aula.professor}</strong>
              </p>

              <span>
                {aula.data} • {aula.horario}
              </span>
            </div>

            <div className="aula-acoes">
              <span
                className={`status ${
                  aula.status === 'Presença registrada'
                    ? 'registrada'
                    : aula.status === 'Em andamento'
                    ? 'andamento'
                    : 'agendada'
                }`}
              >
                {aula.status}
              </span>

              <button onClick={() => abrirRegistro(aula)}>
                Registrar presença
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}

export default Chamadas