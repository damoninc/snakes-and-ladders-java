import type { GameState, Player } from '../api/gameClient'

type HeroSectionProps = {
  state: GameState | null
  playersInput: number
  onPlayersInputChange: (value: number) => void
  onStartNewGame: () => void
  onRoll: () => void
  onRefresh: () => void
  loading: boolean
  actionLabel: string | null
  error: string | null
  currentPlayer: Player | null
}

const HeroSection = ({
  state,
  playersInput,
  onPlayersInputChange,
  onStartNewGame,
  onRoll,
  onRefresh,
  loading,
  actionLabel,
  error,
  currentPlayer,
}: HeroSectionProps) => (
  <header className="hero">
    <div className="hero-copy">
      <p className="eyebrow">Snakes and Ladders</p>
      <h1>Make your move</h1>
      <p className="subtitle">
        A live board powered by your API. Roll to advance, climb ladders, and
        dodge snakes.
      </p>
    </div>
    <div className="hero-actions">
      <div className="control">
        <label htmlFor="playerCount">Players</label>
        <div className="control-row">
          <input
            id="playerCount"
            type="number"
            min={2}
            max={8}
            value={playersInput}
            onChange={(event) =>
              onPlayersInputChange(Number(event.target.value))
            }
          />
          <button
            type="button"
            className="ghost"
            onClick={onStartNewGame}
            disabled={loading}
          >
            New game
          </button>
        </div>
      </div>
      <div className="control action-control">
        <button
          type="button"
          className="primary"
          onClick={onRoll}
          disabled={loading || !currentPlayer || state?.finished}
        >
          {currentPlayer ? `Roll for ${currentPlayer.name}` : 'Roll turn'}
        </button>
        <button
          type="button"
          className="ghost"
          onClick={onRefresh}
          disabled={loading}
        >
          Refresh
        </button>
      </div>
      <div className="status">
        {actionLabel && <span className="status-pill">{actionLabel}</span>}
        {state?.finished && state.winnerName && (
          <span className="status-pill success">
            Winner: {state.winnerName}
          </span>
        )}
        {error && <span className="status-pill danger">{error}</span>}
      </div>
    </div>
  </header>
)

export default HeroSection
