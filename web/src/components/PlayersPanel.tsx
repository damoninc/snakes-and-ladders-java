import type { GameState, Player } from '../api/gameClient'

type PlayersPanelProps = {
  state: GameState | null
  players: Player[]
}

const PlayersPanel = ({ state, players }: PlayersPanelProps) => (
  <section className="panel players-panel">
    <div className="panel-header">
      <h2>Players</h2>
      <span className="muted">{players.length} active</span>
    </div>
    <div className="players-list">
      {players.map((player) => {
        const progress =
          state && state.boardSize
            ? Math.min(100, (player.position / state.boardSize) * 100)
            : 0
        const isCurrent = player.id === state?.currentPlayerId
        return (
          <div
            key={player.id}
            className={`player-card ${isCurrent ? 'current' : ''}`}
          >
            <div className="player-meta">
              <div>
                <p className="player-name">{player.name}</p>
                <p className="player-sub">
                  Space {player.position} / {state?.boardSize ?? '--'}
                </p>
              </div>
              {isCurrent && <span className="turn-flag">Your turn</span>}
            </div>
            <div className="progress">
              <span style={{ width: `${progress}%` }} />
            </div>
          </div>
        )
      })}
      {!players.length && <p className="empty">No players loaded yet.</p>}
    </div>
  </section>
)

export default PlayersPanel
