export type Player = {
  name: string
  position: number
  id: number
}

export type GameState = {
  players: Record<string, Player>
  turnOrder: number[]
  boardSize: number
  currentPlayerId: number | null
  winnerId: number | null
  lastRoll: number | null
  finished: boolean
  winnerName: string | null
}

const API_BASE = import.meta.env.VITE_BASE_API_URL || "";

const readErrorMessage = async (response: Response) => {
  try {
    const data = await response.json()
    if (data?.message) {
      return data.message as string
    }
    if (data?.error) {
      return data.error as string
    }
  } catch {
    return response.statusText
  }
  return response.statusText
}

const handleResponse = async <T>(response: Response): Promise<T> => {
  if (!response.ok) {
    throw new Error(await readErrorMessage(response))
  }
  return (await response.json()) as T
}

export const fetchState = async () =>
  handleResponse<GameState>(await fetch(`${API_BASE}/state`))

export const startNewGame = async (players: number) =>
  handleResponse<GameState>(
    await fetch(`${API_BASE}/start?players=${players}`, {
      method: 'POST',
    }),
  )

export const rollForPlayer = async (playerId: number) =>
  handleResponse<GameState>(
    await fetch(`${API_BASE}/roll/${playerId}`, { method: 'POST' }),
  )
