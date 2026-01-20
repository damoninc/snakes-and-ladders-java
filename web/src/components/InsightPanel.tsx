import type { GameState, Player } from '../api/gameClient'

type InsightPanelProps = {
  state: GameState | null
  currentPlayer: Player | null
}

const InsightPanel = ({ state, currentPlayer }: InsightPanelProps) => (
  <section className="panel insight-panel">
    <h2>Next move</h2>
    <p className="insight-copy">
      Tap roll to progress the match. The API validates turn order and applies
      snakes or ladders automatically.
    </p>
    <div className="insight-details">
      <div>
        <p className="label">Current turn</p>
        <p className="value">{currentPlayer ? currentPlayer.name : '--'}</p>
      </div>
      <div>
        <p className="label">Game status</p>
        <p className="value">
          {state?.finished ? 'Completed' : 'Awaiting roll'}
        </p>
      </div>
    </div>
  </section>
)

export default InsightPanel
