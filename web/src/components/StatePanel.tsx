import type { GameState, Player } from '../api/gameClient'

type StatePanelProps = {
  state: GameState | null
  currentPlayer: Player | null
}

const StatePanel = ({ state, currentPlayer }: StatePanelProps) => (
  <section className="panel state-panel">
    <div className="panel-header">
      <h2>Live state</h2>
      <span className={`badge ${state?.finished ? 'end' : 'live'}`}>
        {state?.finished ? 'Finished' : 'In play'}
      </span>
    </div>
    <div className="state-grid">
      <div>
        <p className="label">Board size</p>
        <p className="value">{state?.boardSize ?? '--'}</p>
      </div>
      <div>
        <p className="label">Current player</p>
        <p className="value">{currentPlayer ? currentPlayer.name : '--'}</p>
      </div>
      <div>
        <p className="label">Last roll</p>
        <p className="value">{state?.lastRoll ?? '--'}</p>
      </div>
      <div>
        <p className="label">Turn order</p>
        <p className="value">
          {state?.turnOrder?.length ? state.turnOrder.join(', ') : '--'}
        </p>
      </div>
    </div>
  </section>
)

export default StatePanel
